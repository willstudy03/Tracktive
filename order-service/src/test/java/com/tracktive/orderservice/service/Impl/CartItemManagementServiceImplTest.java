package com.tracktive.orderservice.service.Impl;

import com.tracktive.orderservice.model.DTO.CartItemManagementRequestDTO;
import com.tracktive.orderservice.model.DTO.CartItemManagementResponseDTO;
import com.tracktive.orderservice.model.DTO.CartItemRequestDTO;
import com.tracktive.orderservice.service.CartItemManagementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CartItemManagementServiceImplTest {

    @Autowired
    private CartItemManagementService cartItemManagementService;

    @Test
    void addProductToCart() {
        CartItemManagementRequestDTO requestDTO =
                new CartItemManagementRequestDTO("retailer123", "061084d7-5ec8-47a0-8d7e-81bb556290e9", 4);

        CartItemManagementResponseDTO responseDTO = cartItemManagementService.addProductToCart(requestDTO);

        assertNotNull(responseDTO);
        assertEquals(responseDTO.getSupplierProductId(), "061084d7-5ec8-47a0-8d7e-81bb556290e9");
    }
}