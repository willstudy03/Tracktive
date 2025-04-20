package com.tracktive.paymentservice.kafka;

import OrderAction.events.PaymentResultEvent;
import com.tracktive.paymentservice.exception.FailedToSendEventException;
import com.tracktive.paymentservice.model.DTO.PaymentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
* Description: Order Event Producer
* @author William Theo
* @date 17/4/2025
*/
@Service
public class OrderEventProducer {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    private static final Logger log = LoggerFactory.getLogger(OrderEventProducer.class);

    @Autowired
    public OrderEventProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPaymentGenerated(PaymentDTO paymentDTO){

        log.info("OrderEventProducer(PAYMENT_GENERATED_EVENT): Prepare payment generated with Order ID {}", paymentDTO.getOrderId());

        PaymentResultEvent event = PaymentResultEvent.newBuilder()
                .setOrderId(paymentDTO.getOrderId())
                .setPaymentId(paymentDTO.getId())
                .build();

        try {

            kafkaTemplate.send("payment-generated", event.toByteArray());

            log.info("OrderEventProducer(PAYMENT_RESULT_EVENT): Sent PAYMENT_GENERATED event with Order ID {}", paymentDTO.getOrderId());

        } catch (Exception e) {

            log.error("OrderEventProducer(PAYMENT_RESULT_EVENT): Failed to send PAYMENT_GENERATED event for Order ID {}. Error: {}", paymentDTO.getOrderId(), e.getMessage());

            throw new FailedToSendEventException("Failed to send PAYMENT_GENERATED event for order ID " + paymentDTO.getOrderId(), e);
        }
    }

}
