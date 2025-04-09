package com.tracktive.productservice.kafka;

import OrderAction.events.StockDeductionSuccessEvent;
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

    private KafkaTemplate<String, byte[]> kafkaTemplate;

    private static final Logger log = LoggerFactory.getLogger(OrderEventProducer.class);

    @Autowired
    public OrderEventProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendStockDeductionSuccessEvent(String orderId){

        log.info("OrderEventProducer(STOCK_DEDUCTION_SUCCESS_EVENT): Prepare stock deduction success event with Order ID {}", orderId);

        StockDeductionSuccessEvent event = StockDeductionSuccessEvent.newBuilder()
                .setOrderId(orderId)
                .setEventType("STOCK_DEDUCTION_SUCCESS")
                .build();

        try {
            kafkaTemplate.send("order", event.toByteArray());
            log.info("OrderEventProducer(STOCK_DEDUCTION_SUCCESS_EVENT): Sent stock deduction success event with Order ID {}", orderId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
