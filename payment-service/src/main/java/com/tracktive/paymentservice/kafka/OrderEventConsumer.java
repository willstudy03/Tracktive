package com.tracktive.paymentservice.kafka;

import OrderAction.events.PaymentCancellationEvent;
import OrderAction.events.PaymentRequestEvent;
import com.google.protobuf.InvalidProtocolBufferException;
import com.tracktive.paymentservice.model.DTO.PaymentDTO;
import com.tracktive.paymentservice.model.DTO.PaymentRequestDTO;
import com.tracktive.paymentservice.model.Enum.PaymentStatus;
import com.tracktive.paymentservice.service.PaymentService;
import com.tracktive.paymentservice.util.converter.PaymentConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

/**
* Description: Order Event Consumer
* @author William Theo
* @date 11/4/2025
*/
@Service
public class OrderEventConsumer {

    private final OrderEventProducer orderEventProducer;

    private final PaymentService paymentService;

    private final TransactionTemplate transactionTemplate;

    private final static Logger log = LoggerFactory.getLogger(OrderEventConsumer.class);

    @Autowired
    public OrderEventConsumer(OrderEventProducer orderEventProducer, PaymentService paymentService, TransactionTemplate transactionTemplate) {
        this.orderEventProducer = orderEventProducer;
        this.paymentService = paymentService;
        this.transactionTemplate = transactionTemplate;
    }

    @KafkaListener(topics = "payment-requests", groupId = "payment-service")
    public void consumePaymentRequests(byte[] event, Acknowledgment ack) {
        boolean processSucceeded = false;

        try {
            PaymentRequestEvent paymentRequestEvent = PaymentRequestEvent.parseFrom(event);
            String orderId = paymentRequestEvent.getOrderId();

            log.info("Received payment request for Order ID: {}", orderId);

            processSucceeded = processPaymentRequest(paymentRequestEvent);

        } catch (InvalidProtocolBufferException e) {
            log.error("Failed to deserialize payment request event", e);
            processSucceeded = true; // Non-recoverable error, acknowledge to avoid redelivery
        } catch (Exception e) {
            log.error("Unexpected error processing payment request", e);
            processSucceeded = false;
        } finally {
            if (processSucceeded) {
                ack.acknowledge();
                log.info("Payment request processing completed and acknowledged");
            } else {
                log.warn("Payment request processing failed, message not acknowledged");
            }
        }
    }

    private boolean processPaymentRequest(PaymentRequestEvent event) {
        // Guard clause for null event
        if (event == null) {
            log.error("Payment request event is null");
            return false;
        }

        try {
            // Use Boolean object to handle potential null return from transaction template
            Boolean result = transactionTemplate.execute(status -> {
                try {
                    // Null check and get orderId safely
                    String orderId = event.getOrderId();
                    if (orderId == null || orderId.trim().isEmpty()) {
                        log.error("Order ID is null or empty in payment request event");
                        return Boolean.FALSE;
                    }

                    // Map the event to the DTO with null safety
                    PaymentRequestDTO paymentRequestDTO;
                    try {
                        paymentRequestDTO = PaymentConverter.toPaymentRequestDTO(event);
                        if (paymentRequestDTO == null) {
                            log.error("Failed to convert payment request for Order ID: {}", orderId);
                            return Boolean.FALSE;
                        }
                    } catch (Exception e) {
                        log.error("Error converting payment request for Order ID: {}", orderId, e);
                        return Boolean.FALSE;
                    }

                    // Create the payment record
                    PaymentDTO paymentDTO;
                    try {
                        paymentDTO = paymentService.addPayment(paymentRequestDTO);
                    } catch (Exception e) {
                        log.error("Error creating payment for Order ID: {}", orderId, e);
                        status.setRollbackOnly();
                        return Boolean.FALSE;
                    }

                    // Verify payment was created successfully
                    if (paymentDTO != null && paymentDTO.getId() != null) {
                        try {
                            orderEventProducer.sendPaymentGenerated(paymentDTO);
                        } catch (Exception e) {
                            log.error("Error sending payment generated for Order ID: {}", orderId, e);
                            status.setRollbackOnly();
                            return Boolean.FALSE;
                        }
                        log.info("Successfully created payment with ID {} for Order ID: {}",
                                paymentDTO.getId(), orderId);
                        return Boolean.TRUE;
                    } else {
                        log.error("Failed to create payment for Order ID: {}", orderId);
                        status.setRollbackOnly();
                        return Boolean.FALSE;
                    }

                } catch (Exception e) {
                    log.error("Error processing payment request", e);
                    status.setRollbackOnly();
                    return Boolean.FALSE;
                }
            });

            // Explicit null check before returning
            return result != null && result;

        } catch (Exception e) {
            log.error("Transaction execution failed", e);
            return false;
        }
    }

    @KafkaListener(topics ="payment-cancellation-requests", groupId = "payment-service")
    public void consumePaymentCancellationRequests(byte[] event, Acknowledgment ack){
        boolean processSucceeded = false;

        try{
            PaymentCancellationEvent paymentCancellationEvent = PaymentCancellationEvent.parseFrom(event);
            String paymentId = paymentCancellationEvent.getPaymentId();

            log.info("Received payment cancellation request for Payment ID: {}", paymentId);

            processSucceeded = processPaymentCancellationRequest(paymentId);

        } catch (InvalidProtocolBufferException e) {
            log.error("Deserialization error for payment cancellation request event", e);
            // Deserialization errors are non-recoverable, so we should acknowledge
            processSucceeded = true;
        }  catch (Exception e) {
            log.error("Unexpected error while processing PaymentCancellationEvent", e);
            processSucceeded = false;
        } finally {
            if (processSucceeded) {
                ack.acknowledge();
                log.info("Payment Cancellation message acknowledged");
            } else {
                log.warn("Payment Cancellation message not acknowledged, will be redelivered by Kafka");
            }
        }
    }

    private boolean processPaymentCancellationRequest(String paymentId) {
        // Guard clause for null event
        if (paymentId == null) {
            log.error("Process Payment Cancellation: Payment ID is null");
            return false;
        }

        try {
            // Use Boolean object to handle potential null return from transaction template
            Boolean result = transactionTemplate.execute(status -> {
                try {
                    PaymentDTO paymentDTO = paymentService.lockPaymentById(paymentId);
                    paymentDTO.setPaymentStatus(PaymentStatus.CANCELED);

                    // You need to save the updated payment
                    paymentService.updatePayment(paymentDTO);

                    // Return TRUE on success
                    return Boolean.TRUE;
                } catch (Exception e) {
                    log.error("Error processing payment cancellation request", e);
                    status.setRollbackOnly();
                    return Boolean.FALSE;
                }
            });
            // Explicit null check before returning
            return result != null && result;
        } catch (Exception e) {
            log.error("Transaction execution failed", e);
            return false;
        }
    }
}
