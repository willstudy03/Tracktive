package com.tracktive.productservice.kafka;

import OrderAction.events.StockDeductionEvent;
import OrderAction.events.StockRestoreEvent;
import com.google.protobuf.InvalidProtocolBufferException;
import com.tracktive.productservice.exception.StockDeductionException;
import com.tracktive.productservice.exception.StockValidationException;
import com.tracktive.productservice.model.DTO.StockItemDTO;
import com.tracktive.productservice.model.DTO.StockManagementRequestDTO;
import com.tracktive.productservice.service.StockManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

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

    private final TransactionTemplate transactionTemplate;

    @Autowired
    public OrderEventConsumer(StockManagementService stockManagementService, OrderEventProducer orderEventProducer, TransactionTemplate transactionTemplate) {
        this.stockManagementService = stockManagementService;
        this.orderEventProducer = orderEventProducer;
        this.transactionTemplate = transactionTemplate;
    }

    @KafkaListener(topics = "stock-deduction-requests", groupId = "product-service")
    public void consumeStockDeductionEvent(byte[] event, Acknowledgment ack) {
        boolean processSucceeded = false;

        try {
            StockDeductionEvent stockDeductionEvent = StockDeductionEvent.parseFrom(event);
            String orderId = stockDeductionEvent.getOrderId();
            log.info("OrderEventConsumer(STOCK_DEDUCTION_EVENT): Received Order Event with Order ID {}", orderId);

            List<StockItemDTO> stockItemDTOs = stockDeductionEvent.getStockItemsList().stream()
                    .map(item -> new StockItemDTO(item.getSupplierProductId(), item.getQuantity()))
                    .toList();

            processSucceeded = processStockDeduction(orderId, stockItemDTOs);

        } catch (InvalidProtocolBufferException e) {
            log.error("Deserialization error", e);
            // Deserialization errors are non-recoverable, so we should acknowledge
            processSucceeded = true;
        } catch (Exception e) {
            log.error("Unexpected error while processing StockDeductionEvent", e);
            // Let Kafka retry for unexpected errors
            processSucceeded = false;
        } finally {
            if (processSucceeded) {
                ack.acknowledge(); // Only acknowledge if everything succeeded
                log.info("Message acknowledged, processing completed successfully");
            } else {
                log.warn("Message not acknowledged, will be redelivered by Kafka");
            }
        }
    }

    private boolean processStockDeduction(String orderId, List<StockItemDTO> stockItems) {
        try {
            Boolean result = transactionTemplate.execute(status -> {
                try {
                    // 1. Deduct stock
                    stockManagementService.deductStock(new StockManagementRequestDTO(stockItems));

                    // 2. If deduction successful, send success event
                    orderEventProducer.sendStockDeductionSuccessEvent(orderId);

                    // If we get here, both stock deduction and success event sending succeeded
                    log.info("Stock deduction and success event succeeded for Order ID: {}", orderId);
                    return Boolean.TRUE;

                } catch (StockValidationException | StockDeductionException e) {
                    // 3. Stock deduction failed, mark for rollback
                    status.setRollbackOnly();
                    log.warn("Stock deduction failed for Order ID: {}", orderId, e);

                    // 4. Try to send failure event (outside of transaction logic but still part of process)
                    try {
                        orderEventProducer.sendStockDeductionFailedEvent(orderId);
                        log.info("Stock deduction failure event sent for Order ID: {}", orderId);
                        return Boolean.TRUE; // Process completed successfully (with failure notification)
                    } catch (Exception eventException) {
                        log.error("Failed to send stock deduction failure event for Order ID: {}", orderId, eventException);
                        return Boolean.FALSE; // Let Kafka retry the whole process
                    }
                } catch (Exception e) {
                    // 5. Other failures (including event sending failure) - roll back transaction
                    status.setRollbackOnly();
                    log.error("Failed during transaction for Order ID: {}", orderId, e);
                    return Boolean.FALSE; // Let Kafka retry the whole process
                }
            });

            // Handle potential null result from transaction execution
            return result != null && result;

        } catch (Exception e) {
            log.error("Transaction execution failed for Order ID: {}", orderId, e);
            return false; // Let Kafka retry the whole process
        }
    }

    @KafkaListener(topics = "stock-restore-requests", groupId = "product-service")
    public void consumeStockRestoreEvent(byte[] event, Acknowledgment ack){

        boolean processSucceeded = false;

        try {
            StockRestoreEvent stockRestoreEvent = StockRestoreEvent.parseFrom(event);
            String orderId = stockRestoreEvent.getOrderId();
            log.info("OrderEventConsumer(STOCK_RESTORE_EVENT): Received stock restore Event with Order ID {}", orderId);

            List<StockItemDTO> stockItemDTOs = stockRestoreEvent.getStockItemsList().stream()
                    .map(item -> new StockItemDTO(item.getSupplierProductId(), item.getQuantity()))
                    .toList();

            processSucceeded = processStockRestore(orderId, stockItemDTOs);

        } catch (InvalidProtocolBufferException e) {
            log.error("Deserialization error", e);
            // Deserialization errors are non-recoverable, so we should acknowledge
            processSucceeded = true;
        } catch (Exception e) {
            log.error("Unexpected error while processing StockRestoreEvent", e);
            // Let Kafka retry for unexpected errors
            processSucceeded = false;
        } finally {
            if (processSucceeded) {
                ack.acknowledge(); // Only acknowledge if everything succeeded
                log.info("Message acknowledged, stock restore process completed successfully");
            } else {
                log.warn("Message not acknowledged, stock restore event will be redelivered by Kafka");
            }
        }
    }

    private boolean processStockRestore(String orderId, List<StockItemDTO> stockItems){
        try{
            Boolean result = transactionTemplate.execute(status -> {
                try {
                    // 1. Deduct stock
                    stockManagementService.addStock(new StockManagementRequestDTO(stockItems));
                    // If we get here, stock restore is success
                    log.info("Stock restore success for Order ID: {}", orderId);
                    return Boolean.TRUE;
                } catch (Exception e) {
                    status.setRollbackOnly();
                    log.error("Failed during transaction for Order ID: {}", orderId, e);
                    return Boolean.FALSE; // Let Kafka retry the whole process
                }
            });
            // Handle potential null result from transaction execution
            return result != null && result;

        } catch (Exception e) {
            log.error("Transaction execution for stock restore failed for Order ID: {}", orderId, e);
            return false; // Let Kafka retry the whole process
        }
    }
}
