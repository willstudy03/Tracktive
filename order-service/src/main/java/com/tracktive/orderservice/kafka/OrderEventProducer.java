package com.tracktive.orderservice.kafka;

import OrderAction.events.OrderCreateEvent;
import com.tracktive.orderservice.model.DTO.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
* Description: Create Order Event Kafka Producer
* @author William Theo
* @date 7/4/2025
*/
@Service
public class OrderEventProducer {

    private static final Logger log = LoggerFactory.getLogger(OrderEventProducer.class);
    private KafkaTemplate<String, byte[]> kafkaTemplate;

    @Autowired
    public OrderEventProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderCreatedEvent(OrderDTO orderDTO){

        OrderCreateEvent event = OrderCreateEvent.newBuilder()
                .setOrderId(orderDTO.getId())
                .setEventType("ORDER_CREATED")
                .build();

        try{
            kafkaTemplate.send("order", event.toByteArray());
        } catch (Exception e) {
            log.error("Error sending order event when creating an order:{}", e);
            throw new RuntimeException(e);
        }
    }
}
