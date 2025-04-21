package com.tracktive.orderservice.service.Impl;

import com.tracktive.orderservice.model.DTO.OrderPlacementRequestDTO;
import com.tracktive.orderservice.model.DTO.OrderPlacementResponseDTO;
import com.tracktive.orderservice.service.OrderPlacementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrderPlacementServiceImplTest {

    @Autowired
    private OrderPlacementService orderPlacementService;
    @Test
    void placeOrder() {
        OrderPlacementRequestDTO requestDTO = new OrderPlacementRequestDTO("retailer_123", "Test");
        OrderPlacementResponseDTO responseDTO = orderPlacementService.placeOrder(requestDTO);
        assertNotNull(responseDTO);
    }
}