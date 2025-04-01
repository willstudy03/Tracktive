package com.tracktive.productservice.service.Impl;

import com.tracktive.productservice.model.DTO.StockItemDTO;
import com.tracktive.productservice.model.DTO.StockManagementRequestDTO;
import com.tracktive.productservice.model.DTO.StockManagementResponseDTO;
import com.tracktive.productservice.service.StockManagementService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StockManagementServiceImplTest {

    @Autowired
    private StockManagementService stockManagementService;

    private static Logger logger = LoggerFactory.getLogger(StockManagementServiceImplTest.class);

    @Test
    void validateStock() {

        // Given: Mock stock item request
        StockItemDTO itemDTO = new StockItemDTO("061084d7-5ec8-47a0-8d7e-81bb556290e9", 50);
        List<StockItemDTO> stockItems = List.of(itemDTO);
        StockManagementRequestDTO requestDTO = new StockManagementRequestDTO(stockItems);

        StockManagementResponseDTO responseDTO = stockManagementService.validateStock(requestDTO);

        assertNotNull(responseDTO);
        assertFalse(responseDTO.getResults().isEmpty());
        assertTrue(responseDTO.getResults().getFirst().isValid());
    }

    @Test
    void deductStock() {

        // Given: Mock stock item request
        StockItemDTO itemDTO = new StockItemDTO("061084d7-5ec8-47a0-8d7e-81bb556290e9", 10);
        List<StockItemDTO> stockItems = List.of(itemDTO);
        StockManagementRequestDTO requestDTO = new StockManagementRequestDTO(stockItems);

        StockManagementResponseDTO responseDTO = stockManagementService.deductStock(requestDTO);

        assertNotNull(responseDTO);
        assertFalse(responseDTO.getResults().isEmpty());
        assertTrue(responseDTO.getResults().getFirst().isValid());
    }
}