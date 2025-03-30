package com.tracktive.orderservice.service;

import com.tracktive.orderservice.model.DTO.CartItemDTO;
import com.tracktive.orderservice.model.DTO.CartItemRequestDTO;

import java.util.List;

/**
* Description: Cart Item Service
* @author William Theo
* @date 20/3/2025
*/
public interface CartItemService {
    // Select operations
    List<CartItemDTO> selectAllCartItems();

    List<CartItemDTO> selectAllByRetailerId(String id);

    CartItemDTO selectCartItemById(String id);

    // Lock operation
    CartItemDTO lockCartItemById(String id);

    // Insert operation
    CartItemDTO addCartItem(CartItemRequestDTO cartItemRequestDTO);

    // Update operation
    CartItemDTO updateCartItem(CartItemDTO cartItemDTO);

    // Delete operation
    void deleteCartItemById(String id);
}
