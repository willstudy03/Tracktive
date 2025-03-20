package com.tracktive.orderservice.service;

import com.tracktive.orderservice.model.DTO.OrderItemDTO;

import java.util.List;

/**
* Description: Order Item Service Interface
* @author William Theo
* @date 20/3/2025
*/
public interface OrderItemService {
    // Select operations
    List<OrderItemDTO> selectAllOrderItems();

    OrderItemDTO selectOrderItemById(String id);

    // Lock operation
    OrderItemDTO lockOrderItemById(String id);

    // Insert operation
    void addOrderItem(OrderItemDTO orderItemDTO);

    // Update operation
    void updateOrderItem(OrderItemDTO orderItemDTO);

    // Delete operation
    void deleteOrderItemById(String id);
}
