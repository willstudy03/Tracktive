package com.tracktive.orderservice.service;

import com.tracktive.orderservice.model.DTO.CartItemManagementRequestDTO;
import com.tracktive.orderservice.model.DTO.CartItemManagementResponseDTO;

/**
* Description: Cart Item Management Service Interface
* @author William Theo
* @date 2/4/2025
*/
public interface CartItemManagementService {
    CartItemManagementResponseDTO addProductToCart(CartItemManagementRequestDTO cartItemManagementRequestDTO);
}
