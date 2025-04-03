package com.tracktive.orderservice.grpc;

import com.tracktive.orderservice.model.DTO.StockItemDTO;
import com.tracktive.orderservice.model.DTO.StockValidationResultDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StockManagementServiceGrpcClientTest {

    @Autowired
    private StockManagementServiceGrpcClient stockManagementServiceGrpcClient;

    @Test
    void validateStock() {
        // Prepare test data
        StockItemDTO stockItem = new StockItemDTO("061084d7-5ec8-47a0-8d7e-81bb556290e9", 5); // SupplierProductID = 1, Quantity = 5
        StockValidationResultDTO stockValidationResultDTO = stockManagementServiceGrpcClient.validateStock(stockItem);
        // Assertions
        assertNotNull(stockValidationResultDTO);
        assertEquals(stockItem.getSupplierProductID(), stockValidationResultDTO.getSupplierProductDTO().getSupplierProductId());
    }
}