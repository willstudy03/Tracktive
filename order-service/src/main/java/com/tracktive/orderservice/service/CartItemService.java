package com.tracktive.orderservice.service;

import com.tracktive.orderservice.model.DTO.CartItemDTO;

import java.util.List;
import java.util.Optional;

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
    void addCartItem(CartItemDTO cartItemDTO);

    // Update operation
    void updateCartItem(CartItemDTO cartItemDTO);

    // Delete operation
    void deleteCartItemById(String id);
}
