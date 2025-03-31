package com.tracktive.orderservice.service;

import com.tracktive.orderservice.model.DTO.OrderDTO;
import com.tracktive.orderservice.model.DTO.OrderRequestDTO;

import java.util.List;

/**
* Description: Order Service Interface
* @author William Theo
* @date 20/3/2025
*/
public interface OrderService {
    // Select operations
    List<OrderDTO> selectAllOrders();

    List<OrderDTO> selectAllOrdersByRetailerId(String id);

    List<OrderDTO> selectAllOrdersBySupplierId(String id);

    OrderDTO selectOrderById(String id);

    // Lock operation
    OrderDTO lockOrderById(String id);

    // Insert operation
    OrderDTO addOrder(OrderRequestDTO orderRequestDTO);

    // Update operation
    OrderDTO updateOrder(OrderDTO orderDTO);

    // Delete operation
    void deleteOrderById(String id);
}
