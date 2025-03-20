package com.tracktive.orderservice.repository;

import com.tracktive.orderservice.model.DTO.CartItemDTO;

import java.util.List;
import java.util.Optional;

/**
* Description: Cart Item Repository Interface
* @author William Theo
* @date 19/3/2025
*/
public interface CartItemRepository {
    // Select operations
    List<CartItemDTO> selectAllCartItems();

    List<CartItemDTO> selectAllByRetailerId(String id);

    Optional<CartItemDTO> selectCartItemById(String id);

    // Lock operation
    Optional<CartItemDTO> lockCartItemById(String id);

    // Insert operation
    boolean addCartItem(CartItemDTO cartItemDTO);

    // Update operation
    boolean updateCartItem(CartItemDTO cartItemDTO);

    // Delete operation
    boolean deleteCartItemById(String id);
}
