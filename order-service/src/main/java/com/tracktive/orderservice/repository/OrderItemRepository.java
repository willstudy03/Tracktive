package com.tracktive.orderservice.repository;

import com.tracktive.orderservice.model.DTO.OrderItemDTO;
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
    List<OrderItemDTO> selectAllOrderItems();

    Optional<OrderItemDTO> selectOrderItemById(String id);

    // Lock operation
    Optional<OrderItemDTO> lockOrderItemById(String id);

    // Insert operation
    boolean addOrderItem(OrderItemDTO orderItemDTO);

    // Update operation
    boolean updateOrderItem(OrderItemDTO orderItemDTO);

    // Delete operation
    boolean deleteOrderItemById(String id);
}
