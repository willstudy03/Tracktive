package com.tracktive.orderservice.repository;

import com.tracktive.orderservice.model.entity.OrderItem;

import java.util.List;
import java.util.Optional;

/**
* Description: Order Item Repository Interface
* @author William Theo
* @date 19/3/2025
*/
public interface OrderItemRepository {
    // Select operations
    List<OrderItem> selectAllOrderItems();

    Optional<OrderItem> selectOrderItemById(String id);

    // Lock operation
    Optional<OrderItem> lockOrderItemById(String id);

    // Insert operation
    boolean addOrderItem(OrderItem orderItem);

    // Update operation
    boolean updateOrderItem(OrderItem orderItem);

    // Delete operation
    boolean deleteOrderItemById(String id);
}
