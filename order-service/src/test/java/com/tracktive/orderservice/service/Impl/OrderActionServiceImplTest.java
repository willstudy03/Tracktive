package com.tracktive.orderservice.service.Impl;

import com.tracktive.orderservice.model.DTO.OrderActionRequestDTO;
import com.tracktive.orderservice.model.DTO.OrderActionResponseDTO;
import com.tracktive.orderservice.service.OrderActionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrderActionServiceImplTest {

    @Autowired
    private OrderActionService orderActionService;
    @Test
    void placeOrder() {
        OrderActionRequestDTO requestDTO = new OrderActionRequestDTO("retailer_123");
        OrderActionResponseDTO responseDTO = orderActionService.placeOrder(requestDTO);
        assertNotNull(responseDTO);
    }
}