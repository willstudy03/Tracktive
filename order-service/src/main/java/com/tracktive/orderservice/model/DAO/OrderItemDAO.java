package com.tracktive.orderservice.model.DAO;

import com.tracktive.orderservice.model.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

/**
* Description: Order Item DAO
* @author William Theo
* @date 19/3/2025
*/
@Mapper
public interface OrderItemDAO {
    // Select operations
    List<OrderItem> selectAllOrderItems();

    Optional<OrderItem> selectOrderItemById(String id);

    // Lock operation
    Optional<OrderItem> lockOrderItemById(String id);

    // Insert operation
    int addOrderItem(OrderItem orderItem);

    // Update operation
    int updateOrderItem(OrderItem orderItem);

    // Delete operation
    int deleteOrderItemById(String id);
}
