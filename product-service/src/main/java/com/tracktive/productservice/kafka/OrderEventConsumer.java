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

    @Autowired
    public OrderEventConsumer(StockManagementService stockManagementService) {
        this.stockManagementService = stockManagementService;
    }

    @KafkaListener(topics = "order", groupId = "product-service")
    @Transactional
    public void consumeStockDeductionEvent(byte[] event){
        try {

            StockDeductionEvent stockDeductionEvent = StockDeductionEvent.parseFrom(event);

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
            stockManagementService.deductStock(new StockManagementRequestDTO(stockItemDTOs));

            log.info("OrderEventConsumer(STOCK_DEDUCTION_EVENT): Stock deduction completed for Order ID: {}", stockDeductionEvent.getOrderId());

            //  After deduction invoke producer to send event to notify next service.
        } catch (StockDeductionException stockDeductionException) {
            // TODO: To send back the STOCK_DEDUCTION_FAILED event


        } catch (InvalidProtocolBufferException e) {
            log.error("OrderEventConsumer(STOCK_DEDUCTION_EVENT): Error deserializing event {}", e.getMessage());
            // TODO: To send back the STOCK_DEDUCTION_FAILED event
        }
    }
}
