package com.tracktive.orderservice.repository;

import com.tracktive.orderservice.model.DTO.OrderDTO;
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
    List<OrderDTO> selectAllOrders();

    List<OrderDTO> selectAllOrdersByRetailerId(String id);

    List<OrderDTO> selectAllOrdersBySupplierId(String id);

    Optional<OrderDTO> selectOrderById(String id);

    // Lock operation
    Optional<OrderDTO> lockOrderById(String id);

    // Insert operation
    boolean addOrder(OrderDTO orderDTO);

    // Update operation
    boolean updateOrder(OrderDTO orderDTO);

    // Delete operation
    boolean deleteOrderById(String id);
}
