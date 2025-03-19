package com.tracktive.orderservice.model.DAO;

import com.tracktive.orderservice.model.entity.CartItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

/**
* Description: Cart Item DAO for mybatis mapping
* @author William Theo
* @date 18/3/2025
*/
@Mapper
public interface CartItemDAO {
    // Select operations
    List<CartItem> selectAllCartItems();

    List<CartItem> selectAllByRetailerId(String id);

    Optional<CartItem> selectCartItemById(String id);

    // Lock operation
    Optional<CartItem> lockCartItemById(String id);

    // Insert operation
    int addCartItem(CartItem cartItem);

    // Update operation
    int updateCartItem(CartItem cartItem);

    // Delete operation
    int deleteCartItemById(String id);
}
