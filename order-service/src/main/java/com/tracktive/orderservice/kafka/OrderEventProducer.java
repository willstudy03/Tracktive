package com.tracktive.orderservice.kafka;

import OrderAction.events.StockDeductionEvent;
import OrderAction.events.StockItem;
import com.tracktive.orderservice.model.DTO.OrderItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void sendOrderCreatedEvent(String orderId, List<OrderItemDTO> orderItemDTOS){

        // Map each OrderItemDTO into ItemStock (from protobuf)
        List<StockItem> stockItems = orderItemDTOS.stream()
                .map(item -> StockItem.newBuilder()
                        .setSupplierProductId(item.getSupplierProductId())
                        .setQuantity(item.getQuantity())
                        .build())
                .toList();

        StockDeductionEvent event = StockDeductionEvent.newBuilder()
                .setOrderId(orderItemDTOS.getFirst().getOrderId())
                .addAllStockItems(stockItems)
                .setEventType("STOCK_DEDUCTION")
                .build();

        try{
            kafkaTemplate.send("order", event.toByteArray());
        } catch (Exception e) {
            log.error("Error sending order event when creating an order:{}", e);
            throw new RuntimeException(e);
        }
    }
}
