package com.tracktive.orderservice.repository.Impl;

import com.tracktive.orderservice.exception.DatabaseOperationException;
import com.tracktive.orderservice.exception.OrderItemAlreadyExistException;
import com.tracktive.orderservice.model.DAO.OrderItemDAO;
import com.tracktive.orderservice.model.DTO.OrderItemDTO;
import com.tracktive.orderservice.model.entity.OrderItem;
import com.tracktive.orderservice.repository.OrderItemRepository;
import com.tracktive.orderservice.util.OrderItemConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
* Description: Order Item Repository Implementation
* @author William Theo
* @date 19/3/2025
*/
@Repository
public class OrderItemRepositoryImpl implements OrderItemRepository {

    private final OrderItemDAO orderItemDAO;

    @Autowired
    public OrderItemRepositoryImpl(OrderItemDAO orderItemDAO) {
        this.orderItemDAO = orderItemDAO;
    }

    @Override
    public List<OrderItemDTO> selectAllOrderItems() {
        return orderItemDAO.selectAllOrderItems()
                .stream()
                .map(OrderItemConverter::toDTO)
                .toList();
    }

    @Override
    public Optional<OrderItemDTO> selectOrderItemById(String id) {
        return orderItemDAO.selectOrderItemById(id).map(OrderItemConverter::toDTO);
    }

    @Override
    public Optional<OrderItemDTO> lockOrderItemById(String id) {
        return orderItemDAO.lockOrderItemById(id).map(OrderItemConverter::toDTO);
    }

    @Override
    public boolean addOrderItem(OrderItemDTO orderItemDTO) {
        try {
            OrderItem orderItem = OrderItemConverter.toEntity(orderItemDTO);
            return orderItemDAO.addOrderItem(orderItem) > 0;
        } catch (DuplicateKeyException e) {
            throw new OrderItemAlreadyExistException("Order Item with id " + orderItemDTO.getId() + " already exists", e);
        } catch (DataAccessException e) {
            throw new DatabaseOperationException("Failed to add Order Item to the database", e);
        }
    }

    @Override
    public boolean updateOrderItem(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = OrderItemConverter.toEntity(orderItemDTO);
        return orderItemDAO.updateOrderItem(orderItem) > 0;
    }

    @Override
    public boolean deleteOrderItemById(String id) {
        return orderItemDAO.deleteOrderItemById(id) > 0;
    }
}
