package com.tracktive.orderservice.model.DAO;

import com.tracktive.orderservice.model.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

/**
* Description: Order DAO for mybatis mapping
* @author William Theo
* @date 15/3/2025
*/
@Mapper
public interface OrderDAO {
    // Select operations
    List<Order> selectAllOrders();

    List<Order> selectAllOrdersByRetailerId(String id);

    List<Order> selectAllOrdersBySupplierId(String id);

    Optional<Order> selectOrderById(String id);

    // Lock operation
    Optional<Order> lockOrderById(String id);

    // Insert operation
    int addOrder(Order order);

    // Update operation
    int updateOrder(Order order);

    // Delete operation
    int deleteOrderById(String id);
}
