package com.tracktive.orderservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
* Description: Create Order Event Kafka Producer
* @author William Theo
* @date 7/4/2025
*/
@Service
public class CreateOrderProducer {

    private KafkaTemplate<String, byte[]> kafkaTemplate;

    @Autowired
    public CreateOrderProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
}
