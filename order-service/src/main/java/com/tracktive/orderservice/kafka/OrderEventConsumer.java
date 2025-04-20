package com.tracktive.orderservice.kafka;

import OrderAction.events.PaymentGeneratedEvent;
import OrderAction.events.PaymentResultEvent;
import OrderAction.events.StockDeductionResultEvent;
import com.google.protobuf.InvalidProtocolBufferException;
import com.tracktive.orderservice.model.DTO.OrderDTO;
import com.tracktive.orderservice.model.Enum.OrderStatus;
import com.tracktive.orderservice.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

/**
* Description: Order Event Consumer
* @author William Theo
* @date 11/4/2025
*/
@Service
public class OrderEventConsumer {

    private final OrderService orderService;

    private final OrderEventProducer orderEventProducer;

    private final TransactionTemplate transactionTemplate;

    private static final Logger log = LoggerFactory.getLogger(OrderEventConsumer.class);

    @Autowired
    public OrderEventConsumer(OrderService orderService, OrderEventProducer orderEventProducer, TransactionTemplate transactionTemplate) {
        this.orderService = orderService;
        this.orderEventProducer = orderEventProducer;
        this.transactionTemplate = transactionTemplate;
    }

    @KafkaListener(topics = "payment-generated", groupId = "order-service")
    @Transactional
    public void consumePaymentGeneratedEvent(byte[] event, Acknowledgment ack) {
        boolean processSucceeded = false;

        try {
            PaymentGeneratedEvent paymentGeneratedEvent = PaymentGeneratedEvent.parseFrom(event);
            String orderId = paymentGeneratedEvent.getOrderId();
            String paymentId = paymentGeneratedEvent.getPaymentId();

            log.info("OrderEventConsumer(PAYMENT_GENERATED_EVENT): Received payment event for Order ID {} with Payment ID {}",
                    orderId, paymentId);

            OrderDTO orderDTO = orderService.lockOrderById(orderId);
            orderDTO.setPaymentId(paymentId);
            orderService.updateOrder(orderDTO);

            processSucceeded = true;
            log.info("Successfully updated order {} with payment ID {}", orderId, paymentId);

        } catch (InvalidProtocolBufferException e) {
            log.error("Deserialization error for payment generated event", e);
            // Deserialization errors are non-recoverable, so we should acknowledge
            processSucceeded = true;
        } catch (Exception e) {
            log.error("Unexpected error while processing PaymentGeneratedEvent", e);
            processSucceeded = false;
        } finally {
            if (processSucceeded) {
                ack.acknowledge();
                log.info("Payment generated event message acknowledged");
            } else {
                log.warn("Payment generated event message not acknowledged, will be redelivered by Kafka");
            }
        }
    }

    @KafkaListener(topics = "payment-results", groupId = "order-service")
    @Transactional
    public void consumePaymentResultEvent(byte[] event, Acknowledgment ack){

        boolean processSucceeded = false;

        try{
            PaymentResultEvent paymentResultEvent = PaymentResultEvent.parseFrom(event);
            String orderId = paymentResultEvent.getOrderId();
            String eventType = paymentResultEvent.getEventType();

            log.info("OrderEventConsumer(PAYMENT_RESULTS_EVENT): Received {} event with Order ID {}",
                    eventType, orderId);

            processSucceeded = processPaymentResult(orderId, eventType);

        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);

        } catch (Exception e) {
            log.error("Unexpected error while processing paymentResultEvent", e);
            processSucceeded = false;
        } finally {
            if (processSucceeded) {
                ack.acknowledge();
                log.info("Payment result message acknowledged");
            } else {
                log.warn("Payment result message not acknowledged, will be redelivered by Kafka");
            }
        }
    }

    private boolean processPaymentResult(String orderId, String eventType){
        try{
            Boolean result = transactionTemplate.execute(status -> {
                // Lock the order for update
                OrderDTO orderDTO = orderService.lockOrderById(orderId);
                if (orderDTO == null) {
                    log.error("Order not found with ID: {}", orderId);
                    return Boolean.FALSE;
                }

                switch (eventType) {
                    case "PAYMENT_SUCCESS":
                        orderDTO.setOrderStatus(OrderStatus.PLACED);
                        orderService.updateOrder(orderDTO);
                        log.info("Order status updated to COMPLETED for Order ID: {}", orderId);
                        return Boolean.TRUE;

                    case "PAYMENT_FAILED":
                        orderDTO.setOrderStatus(OrderStatus.CANCELLED);
                        OrderDTO updatedOrder = orderService.updateOrder(orderDTO);

                        try {
                            // TODO: Send revert stock - if this fails, the transaction will roll back
                            log.info("Stock revert request sent successfully for Order ID: {}", orderId);
                            return Boolean.TRUE;
                        } catch (Exception e) {
                            log.error("Failed to send stock revert request for Order ID: {}", orderId, e);
                            // Explicitly mark transaction for rollback
                            status.setRollbackOnly();
                            return Boolean.FALSE;
                        }

                    default:
                        log.warn("Unknown event type for processing payment result: {} for Order ID: {}", eventType, orderId);
                        return Boolean.FALSE;
                }
            });
            return result != null && result;
        } catch (Exception e) {
            log.error("Transaction execution failed to processed payment result of Order ID: {}", orderId, e);
            return false;
        }
    }

    @KafkaListener(topics = "stock-deduction-results", groupId = "order-service")
    public void consumeStockDeductionResultsEvent(byte[] event, Acknowledgment ack) {
        boolean processSucceeded = false;

        try {
            StockDeductionResultEvent stockDeductionResultEvent = StockDeductionResultEvent.parseFrom(event);
            String orderId = stockDeductionResultEvent.getOrderId();
            String eventType = stockDeductionResultEvent.getEventType();

            log.info("OrderEventConsumer(STOCK_DEDUCTION_RESULTS_EVENT): Received {} event with Order ID {}",
                    eventType, orderId);

            processSucceeded = processStockDeductionResult(orderId, eventType);

        } catch (InvalidProtocolBufferException e) {
            log.error("Deserialization error for stock deduction result event", e);
            // Deserialization errors are non-recoverable, so we should acknowledge
            processSucceeded = true;
        } catch (Exception e) {
            log.error("Unexpected error while processing StockDeductionResultEvent", e);
            processSucceeded = false;
        } finally {
            if (processSucceeded) {
                ack.acknowledge();
                log.info("Stock deduction result message acknowledged");
            } else {
                log.warn("Stock deduction result message not acknowledged, will be redelivered by Kafka");
            }
        }
    }

    private boolean processStockDeductionResult(String orderId, String eventType) {
        try {
            Boolean result = transactionTemplate.execute(status -> {
                try {
                    // Lock the order for update
                    OrderDTO orderDTO = orderService.lockOrderById(orderId);
                    if (orderDTO == null) {
                        log.error("Order not found with ID: {}", orderId);
                        return Boolean.FALSE;
                    }

                    switch (eventType) {
                        case "STOCK_DEDUCTION_FAILED":
                            orderDTO.setOrderStatus(OrderStatus.CANCELLED);
                            orderService.updateOrder(orderDTO);
                            log.info("Order status updated to CANCELLED for Order ID: {}", orderId);
                            return Boolean.TRUE;

                        case "STOCK_DEDUCTION_SUCCESS":
                            orderDTO.setOrderStatus(OrderStatus.READY_FOR_PAYMENT);
                            OrderDTO updatedOrder = orderService.updateOrder(orderDTO);

                            try {
                                // Send payment request - if this fails, the transaction will roll back
                                orderEventProducer.sendPaymentRequestEvent(updatedOrder);
                                log.info("Payment request sent successfully for Order ID: {}", orderId);
                                return Boolean.TRUE;
                            } catch (Exception e) {
                                log.error("Failed to send payment request for Order ID: {}", orderId, e);
                                // Explicitly mark transaction for rollback
                                status.setRollbackOnly();
                                return Boolean.FALSE;
                            }

                        default:
                            log.warn("Unknown event type: {} for Order ID: {}", eventType, orderId);
                            return Boolean.FALSE;
                    }

                } catch (Exception e) {
                    log.error("Error processing stock deduction result for Order ID: {}", orderId, e);
                    status.setRollbackOnly();
                    return Boolean.FALSE;
                }
            });

            return result != null && result;

        } catch (Exception e) {
            log.error("Transaction execution failed for Order ID: {}", orderId, e);
            return false;
        }
    }

}
