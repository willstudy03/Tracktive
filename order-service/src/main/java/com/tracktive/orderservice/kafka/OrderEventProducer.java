package com.tracktive.orderservice.kafka;

import OrderAction.events.*;
import com.tracktive.orderservice.exception.FailedToPlaceOrderException;
import com.tracktive.orderservice.exception.FailedToSendEventException;
import com.tracktive.orderservice.model.DTO.OrderDTO;
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

    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    private static final Logger log = LoggerFactory.getLogger(OrderEventProducer.class);

    @Autowired
    public OrderEventProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendStockDeductionEvent(String orderId, List<OrderItemDTO> orderItemDTOS){

        log.info("OrderEventProducer(STOCK_DEDUCTION_EVENT): Prepare stock deduction with Order ID {}", orderId);

        // Map each OrderItemDTO into ItemStock (from protobuf)
        List<StockItem> stockItems = orderItemDTOS.stream()
                .map(item -> StockItem.newBuilder()
                        .setSupplierProductId(item.getSupplierProductId())
                        .setQuantity(item.getQuantity())
                        .build())
                .toList();

        StockDeductionEvent event = StockDeductionEvent.newBuilder()
                .setOrderId(orderId)
                .addAllStockItems(stockItems)
                .build();

        try {
            kafkaTemplate.send("stock-deduction-requests", event.toByteArray());
            log.info("OrderEventProducer(STOCK_DEDUCTION_EVENT): Sent stock deduction Event with Order ID {}", orderId);
        } catch (Exception e) {
            log.error("OrderEventProducer(STOCK_DEDUCTION_EVENT): Failed to send event for Order ID {}. Error: {}", orderId, e.getMessage());
            throw new FailedToPlaceOrderException(e.getMessage());
        }
    }

    public void sendStockRestockEvent(String orderId, List<OrderItemDTO> orderItemDTOS){

        log.info("OrderEventProducer(STOCK_RESTORE_EVENT): Prepare stock restore with Order ID {}", orderId);

        // Map each OrderItemDTO into ItemStock (from protobuf)
        List<RestoreStockItem> stockItems = orderItemDTOS.stream()
                .map(item -> RestoreStockItem.newBuilder()
                        .setSupplierProductId(item.getSupplierProductId())
                        .setQuantity(item.getQuantity())
                        .build())
                .toList();

        StockRestoreEvent event = StockRestoreEvent.newBuilder()
                .setOrderId(orderId)
                .addAllStockItems(stockItems)
                .build();

        try {
            kafkaTemplate.send("stock-restore-requests", event.toByteArray());
            log.info("OrderEventProducer(STOCK_RESTORE_EVENT): Sent stock restore Event with Order ID {}", orderId);
        } catch (Exception e) {
            log.error("OrderEventProducer(STOCK_RESTORE_EVENT): Failed to send event for Order ID {}. Error: {}", orderId, e.getMessage());
            throw new FailedToPlaceOrderException(e.getMessage());
        }
    }

    public void sendPaymentRequestEvent(OrderDTO orderDTO){

        log.info("OrderEventProducer(PAYMENT_REQUEST_EVENT): Prepare payment request with Order ID {}", orderDTO.getId());

        PaymentRequestEvent event = PaymentRequestEvent.newBuilder()
                .setOrderId(orderDTO.getId())
                .setUserId(orderDTO.getRetailerId())
                .setAmount(orderDTO.getTotalAmount().doubleValue())
                .build();
        try {
            kafkaTemplate.send("payment-requests", event.toByteArray());
            log.info("OrderEventProducer(PAYMENT_REQUEST_EVENT): Sent payment request Event with Order ID {}", orderDTO.getId());
        } catch (Exception e) {
            log.error("OrderEventProducer(PAYMENT_REQUEST_EVENT): Failed to send payment request event for Order ID {}. Error: {}", orderDTO.getId(), e.getMessage());
            throw new FailedToSendEventException(e.getMessage());
        }
    }
}
