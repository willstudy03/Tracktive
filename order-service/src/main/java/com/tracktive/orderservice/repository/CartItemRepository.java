package com.tracktive.orderservice.repository;

import com.tracktive.orderservice.model.entity.CartItem;

import java.util.List;
import java.util.Optional;

/**
* Description: Cart Item Repository Interface
* @author William Theo
* @date 19/3/2025
*/
public interface CartItemRepository {
    // Select operations
    List<CartItem> selectAllCartItems();

    List<CartItem> selectAllByRetailerId(String id);

    Optional<CartItem> selectCartItemById(String id);

    // Lock operation
    Optional<CartItem> lockCartItemById(String id);

    // Insert operation
    boolean addCartItem(CartItem cartItem);

    // Update operation
    boolean updateCartItem(CartItem cartItem);

    // Delete operation
    boolean deleteCartItemById(String id);
}
