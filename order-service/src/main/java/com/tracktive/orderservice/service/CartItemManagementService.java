package com.tracktive.orderservice.service;

import com.tracktive.orderservice.model.DTO.CartItemManagementRequestDTO;
import com.tracktive.orderservice.model.DTO.CartItemManagementResponseDTO;

import java.util.List;

/**
* Description: Cart Item Management Service Interface
* @author William Theo
* @date 2/4/2025
*/
public interface CartItemManagementService {

    List<CartItemManagementResponseDTO> selectCartItems(String userId);

    CartItemManagementResponseDTO addCartItem(CartItemManagementRequestDTO cartItemManagementRequestDTO);

    CartItemManagementResponseDTO updateCartItem(CartItemManagementResponseDTO cartItemManagementResponseDTO);
}
