package com.tracktive.productservice.kafka;

import OrderAction.events.StockDeductionResultEvent;
import com.tracktive.productservice.exception.FailedToSendEventException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
* Description:
* @author William Theo
* @date 9/4/2025
*/
@Service
public class OrderEventProducer {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    private static final Logger log = LoggerFactory.getLogger(OrderEventProducer.class);

    @Autowired
    public OrderEventProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendStockDeductionSuccessEvent(String orderId){

        log.info("OrderEventProducer(STOCK_DEDUCTION_SUCCESS_EVENT): Prepare stock deduction success event with Order ID {}", orderId);

        StockDeductionResultEvent event = StockDeductionResultEvent.newBuilder()
                .setOrderId(orderId)
                .setEventType("STOCK_DEDUCTION_SUCCESS")
                .build();

        try {
            kafkaTemplate.send("stock-deduction-results", event.toByteArray());

            log.info("OrderEventProducer(STOCK_DEDUCTION_SUCCESS_EVENT): Sent stock deduction success event with Order ID {}", orderId);

        } catch (Exception e) {

            log.info("OrderEventProducer(STOCK_DEDUCTION_SUCCESS_EVENT): Failed to send stock deduction success event with Order ID {}", orderId);

            throw new FailedToSendEventException("Failed to send STOCK_DEDUCTION_SUCCESS event for order ID " + orderId, e);
        }
    }

    public void sendStockDeductionFailedEvent(String orderId){

        log.info("OrderEventProducer(STOCK_DEDUCTION_FAILED_EVENT): Prepare stock deduction failed event with Order ID {}", orderId);

        StockDeductionResultEvent event = StockDeductionResultEvent.newBuilder()
                .setOrderId(orderId)
                .setEventType("STOCK_DEDUCTION_FAILED")
                .build();

        try {
            kafkaTemplate.send("stock-deduction-results", event.toByteArray());

            log.info("OrderEventProducer(STOCK_DEDUCTION_FAILED_EVENT): Sent stock deduction failed event with Order ID {}", orderId);

        } catch (Exception e) {

            log.info("OrderEventProducer(STOCK_DEDUCTION_FAILED_EVENT): Failed to send stock deduction success event with Order ID {}", orderId);

            throw new FailedToSendEventException("Failed to send STOCK_DEDUCTION_FAILED event for order ID " + orderId, e);
        }
    }
}
