package com.tracktive.productservice.kafka;

import OrderAction.events.StockDeductionEvent;
import com.google.protobuf.InvalidProtocolBufferException;
import com.tracktive.productservice.exception.StockDeductionException;
import com.tracktive.productservice.model.DTO.StockItemDTO;
import com.tracktive.productservice.model.DTO.StockManagementRequestDTO;
import com.tracktive.productservice.service.StockManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* Description: Order Event Consumer
* @author William Theo
* @date 9/4/2025
*/
@Service
public class OrderEventConsumer {

    private static final Logger log = LoggerFactory.getLogger(OrderEventConsumer.class);

    private final StockManagementService stockManagementService;

    private final OrderEventProducer orderEventProducer;

    @Autowired
    public OrderEventConsumer(StockManagementService stockManagementService, OrderEventProducer orderEventProducer) {
        this.stockManagementService = stockManagementService;
        this.orderEventProducer = orderEventProducer;
    }

    @KafkaListener(topics = "order", groupId = "product-service")
    @Transactional
    public void consumeStockDeductionEvent(byte[] event){

        StockDeductionEvent stockDeductionEvent = null;

        try{
            stockDeductionEvent = StockDeductionEvent.parseFrom(event);
        } catch (InvalidProtocolBufferException e) {
            log.error("OrderEventConsumer(STOCK_DEDUCTION_EVENT): Error deserializing event {}", e.getMessage());
        }

        // Check if stockDeductionEvent is null (in case of deserialization failure)
        if (stockDeductionEvent == null) {
            log.error("OrderEventConsumer(STOCK_DEDUCTION_EVENT): Received null StockDeductionEvent, cannot process.");
            return; // Exit the method if event is null
        }

        log.info("OrderEventConsumer(STOCK_DEDUCTION_EVENT): Received Order Event with Order ID {}", stockDeductionEvent.getOrderId());

        if (!stockDeductionEvent.getEventType().equals("STOCK_DEDUCTION")){
            log.info("OrderEventConsumer(STOCK_DEDUCTION_EVENT): Ignore event with unexpected event type: {}", stockDeductionEvent.getEventType());
            return;
        }

        // Map Protobuf StockItems to DTOs
        List<StockItemDTO> stockItemDTOs = stockDeductionEvent.getStockItemsList().stream()
                .map(item -> new StockItemDTO(item.getSupplierProductId(), item.getQuantity()))
                .toList();

        // Deduct stock
        try{
            stockManagementService.deductStock(new StockManagementRequestDTO(stockItemDTOs));
        } catch (StockDeductionException e){
            log.info("OrderEventConsumer(STOCK_DEDUCTION_EVENT): Stock deduction failed for Order ID: {}", stockDeductionEvent.getOrderId());
            orderEventProducer.sendStockDeductionFailedEvent(stockDeductionEvent.getOrderId());
            return;
        }

        log.info("OrderEventConsumer(STOCK_DEDUCTION_EVENT): Stock deduction completed for Order ID: {}", stockDeductionEvent.getOrderId());

        //  After deduction invoke producer to send event to notify next service.
        orderEventProducer.sendStockDeductionSuccessEvent(stockDeductionEvent.getOrderId());
    }
}
