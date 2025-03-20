package com.tracktive.orderservice.service.Impl;

import com.tracktive.orderservice.exception.LockAcquisitionException;
import com.tracktive.orderservice.exception.OrderNotFoundException;
import com.tracktive.orderservice.model.DTO.OrderDTO;
import com.tracktive.orderservice.repository.OrderRepository;
import com.tracktive.orderservice.service.OrderService;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
* Description: Order Service Implementation
* @author William Theo
* @date 20/3/2025
*/
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderDTO> selectAllOrders() {
        return orderRepository.selectAllOrders();
    }

    @Override
    public List<OrderDTO> selectAllOrdersByRetailerId(String id) {
        validateRetailerId(id);
        return orderRepository.selectAllOrdersByRetailerId(id);
    }

    @Override
    public List<OrderDTO> selectAllOrdersBySupplierId(String id) {
        validateSupplierId(id);
        return orderRepository.selectAllOrdersBySupplierId(id);
    }

    @Override
    public OrderDTO selectOrderById(String id) {
        validateId(id);
        return orderRepository.selectOrderById(id)
                .orElseThrow(() -> {
                    logger.warn("Order not found with id: {}", id);
                    return new OrderNotFoundException("Order not found with id: " + id);
                });
    }

    @Override
    @Transactional
    public OrderDTO lockOrderById(String id) {
        validateId(id);
        try {
            return orderRepository.lockOrderById(id)
                    .orElseThrow(() -> {
                        logger.warn("Failed to lock order, order not found with id: {}", id);
                        return new OrderNotFoundException("Order not found with id: " + id);
                    });
        } catch (PersistenceException e) {
            logger.error("Persistence error occurred during lock acquisition for order id: {}", id, e);
            throw new LockAcquisitionException("Failed to acquire lock for order with id: " + id, e);
        } catch (Exception e) {
            logger.error("Unexpected error during order lock for id: {}", id, e);
            throw new RuntimeException("Unexpected error during lock operation", e);
        }
    }

    @Override
    @Transactional
    public void addOrder(OrderDTO orderDTO) {
        validateOrderDTO(orderDTO);
        boolean result = orderRepository.addOrder(orderDTO);
        if (!result) {
            logger.error("Failed to add order with id: {}", orderDTO.getId());
            throw new RuntimeException("Failed to add order with id: " + orderDTO.getId());
        }
        logger.info("Order [{}] added successfully", orderDTO.getId());
    }

    @Override
    @Transactional
    public void updateOrder(OrderDTO orderDTO) {
        validateOrderDTO(orderDTO);
        boolean result = orderRepository.updateOrder(orderDTO);
        if (!result) {
            logger.error("Failed to update order with id: {}", orderDTO.getId());
            throw new OrderNotFoundException("Failed to update order with id: " + orderDTO.getId());
        }
        logger.info("Order [{}] updated successfully", orderDTO.getId());
    }

    @Override
    @Transactional
    public void deleteOrderById(String id) {
        validateId(id);
        boolean result = orderRepository.deleteOrderById(id);
        if (!result) {
            logger.error("Failed to delete order with id: {}", id);
            throw new OrderNotFoundException("Failed to delete order with id: " + id);
        }
        logger.info("Order [{}] deleted successfully", id);

    }

    private void validateRetailerId(String id) {
        if (Objects.isNull(id) || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Retailer ID cannot be null or empty");
        }
    }

    private void validateSupplierId(String id) {
        if (Objects.isNull(id) || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Supplier ID cannot be null or empty");
        }
    }

    private void validateId(String id) {
        if (Objects.isNull(id) || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Order ID cannot be null or empty");
        }
    }

    private void validateOrderDTO(OrderDTO orderDTO) {
        if (Objects.isNull(orderDTO)) {
            throw new IllegalArgumentException("orderDTO cannot be null");
        }
        //@TODO: PARAMETERS VALIDATION
    }
}
