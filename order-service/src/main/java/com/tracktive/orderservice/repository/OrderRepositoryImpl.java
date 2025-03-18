package com.tracktive.orderservice.repository;

import com.tracktive.orderservice.exception.DatabaseOperationException;
import com.tracktive.orderservice.exception.OrderAlreadyExistException;
import com.tracktive.orderservice.model.DAO.OrderDAO;
import com.tracktive.orderservice.model.DTO.OrderDTO;
import com.tracktive.orderservice.model.entity.Order;
import com.tracktive.orderservice.util.OrderConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
* Description: Order Repository Implementation
* @author William Theo
* @date 16/3/2025
*/
@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderDAO orderDAO;

    @Autowired
    public OrderRepositoryImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public List<OrderDTO> selectAllOrders() {
        return Optional.ofNullable(orderDAO.selectAllOrders())
                .orElse(Collections.emptyList())
                .stream()
                .map(OrderConverter::toDTO)
                .toList();
    }

    @Override
    public List<OrderDTO> selectAllOrdersByRetailerId(String id) {
        return orderDAO.selectAllOrdersByRetailerId(id)
                .stream()
                .map(OrderConverter::toDTO)
                .toList();
    }

    @Override
    public List<OrderDTO> selectAllOrdersBySupplierId(String id) {
        return orderDAO.selectAllOrdersBySupplierId(id)
                .stream()
                .map(OrderConverter::toDTO)
                .toList();
    }

    @Override
    public Optional<OrderDTO> selectOrderById(String id) {
        return orderDAO.selectOrderById(id).map(OrderConverter::toDTO);
    }

    @Override
    public Optional<OrderDTO> lockOrderById(String id) {
        return orderDAO.lockOrderById(id).map(OrderConverter::toDTO);
    }

    @Override
    public boolean addOrder(OrderDTO orderDTO) {
        try {
            Order order = OrderConverter.toEntity(orderDTO);
            return orderDAO.addOrder(order) > 0;
        } catch (DuplicateKeyException e) {
            throw new OrderAlreadyExistException("Order with id " + orderDTO.getId() + " already exists", e);
        } catch (DataAccessException e) {
            throw new DatabaseOperationException("Failed to add order to the database", e);
        }
    }

    @Override
    public boolean updateOrder(OrderDTO orderDTO) {
        Order order = OrderConverter.toEntity(orderDTO);
        return orderDAO.updateOrder(order) > 0;
    }

    @Override
    public boolean deleteOrderById(String id) {
        return orderDAO.deleteOrderById(id) > 0;
    }
}
