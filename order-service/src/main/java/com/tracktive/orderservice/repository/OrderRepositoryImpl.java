package com.tracktive.orderservice.repository;

import com.tracktive.orderservice.exception.DatabaseOperationException;
import com.tracktive.orderservice.exception.OrderAlreadyExistException;
import com.tracktive.orderservice.model.DAO.OrderDAO;
import com.tracktive.orderservice.model.entity.Order;
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
    public List<Order> selectAllOrders() {
        return Optional.ofNullable(orderDAO.selectAllOrders())
                .orElse(Collections.emptyList());
    }

    @Override
    public List<Order> selectAllOrdersByRetailerId(String id) {
        return orderDAO.selectAllOrdersByRetailerId(id);
    }

    @Override
    public List<Order> selectAllOrdersBySupplierId(String id) {
        return orderDAO.selectAllOrdersBySupplierId(id);
    }

    @Override
    public Optional<Order> selectOrderById(String id) {
        return orderDAO.selectOrderById(id);
    }

    @Override
    public Optional<Order> lockOrderById(String id) {
        return orderDAO.lockOrderById(id);
    }

    @Override
    public boolean addOrder(Order order) {
        try {
            return orderDAO.addOrder(order) > 0;
        } catch (DuplicateKeyException e) {
            throw new OrderAlreadyExistException("Order with id " + order.getId() + " already exists", e);
        } catch (DataAccessException e) {
            throw new DatabaseOperationException("Failed to add order to the database", e);
        }
    }

    @Override
    public boolean updateOrder(Order order) {
        return orderDAO.updateOrder(order) > 0;
    }

    @Override
    public boolean deleteOrderById(String id) {
        return orderDAO.deleteOrderById(id) > 0;
    }
}
