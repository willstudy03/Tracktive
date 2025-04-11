package com.tracktive.orderservice.kafka;

import com.tracktive.orderservice.service.OrderService;
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

    private final OrderService orderService;

    private final OrderEventProducer orderEventProducer;

    private final TransactionTemplate transactionTemplate;

    @Autowired
    public OrderEventConsumer(OrderService orderService, OrderEventProducer orderEventProducer, TransactionTemplate transactionTemplate) {
        this.orderService = orderService;
        this.orderEventProducer = orderEventProducer;
        this.transactionTemplate = transactionTemplate;
    }

    @KafkaListener(topics = "stock-deduction-results", groupId = "order-service")
    public void consumeStockDeductionResultsEvent(byte[] event, Acknowledgment ack){

    }
}
