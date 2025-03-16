package com.tracktive.orderservice.repository;

import com.tracktive.orderservice.model.entity.Order;

import java.util.List;
import java.util.Optional;

/**
* Description: Order Repository Interface
* @author William Theo
* @date 16/3/2025
*/
public interface OrderRepository {
    // Select operations
    List<Order> selectAllOrders();

    List<Order> selectAllOrdersByRetailerId(String id);

    List<Order> selectAllOrdersBySupplierId(String id);

    Optional<Order> selectOrderById(String id);

    // Lock operation
    Optional<Order> lockOrderById(String id);

    // Insert operation
    boolean addOrder(Order order);

    // Update operation
    boolean updateOrder(Order order);

    // Delete operation
    boolean deleteOrderById(String id);
}
