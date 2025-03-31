package com.tracktive.orderservice.service.Impl;

import com.tracktive.orderservice.exception.LockAcquisitionException;
import com.tracktive.orderservice.exception.OrderItemNotFoundException;
import com.tracktive.orderservice.model.DTO.OrderItemDTO;
import com.tracktive.orderservice.model.DTO.OrderItemRequestDTO;
import com.tracktive.orderservice.repository.OrderItemRepository;
import com.tracktive.orderservice.service.OrderItemService;
import com.tracktive.orderservice.util.converter.OrderItemConverter;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
* Description: Order Item Service Implementation
* @author William Theo
* @date 20/3/2025
*/
@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    private final Validator validator;

    private static final Logger logger = LoggerFactory.getLogger(OrderItemServiceImpl.class);

    @Autowired
    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, Validator validator) {
        this.orderItemRepository = orderItemRepository;
        this.validator = validator;
    }

    @Override
    public List<OrderItemDTO> selectAllOrderItems() {
        return orderItemRepository.selectAllOrderItems();
    }

    @Override
    public OrderItemDTO selectOrderItemById(String id) {
        validateId(id);
        return orderItemRepository.selectOrderItemById(id)
                .orElseThrow(() -> {
                    logger.warn("Order Item not found with id: {}", id);
                    return new OrderItemNotFoundException("Order Item not found with id: " + id);
                });
    }

    @Override
    @Transactional
    public OrderItemDTO lockOrderItemById(String id) {
        validateId(id);
        try {
            return orderItemRepository.lockOrderItemById(id)
                    .orElseThrow(() -> {
                        logger.warn("Failed to lock order item, order item not found with id: {}", id);
                        return new OrderItemNotFoundException("Order item not found with id: " + id);
                    });
        } catch (PersistenceException e) {
            logger.error("Persistence error occurred during lock acquisition for order item id: {}", id, e);
            throw new LockAcquisitionException("Failed to acquire lock for order item with id: " + id, e);
        } catch (Exception e) {
            logger.error("Unexpected error during order item lock for id: {}", id, e);
            throw new RuntimeException("Unexpected error during lock operation", e);
        }
    }

    @Override
    @Transactional
    public OrderItemDTO addOrderItem(OrderItemRequestDTO orderItemRequestDTO) {

        OrderItemDTO orderItemDTO = OrderItemConverter.toDTO(orderItemRequestDTO);

        boolean result = orderItemRepository.addOrderItem(orderItemDTO);
        if (!result) {
            logger.error("Failed to add order item with id: {}", orderItemDTO.getId());
            throw new RuntimeException("Failed to add order item with id: " + orderItemDTO.getId());
        }
        logger.info("Order Item [{}] added successfully", orderItemDTO.getId());

        return orderItemRepository.selectOrderItemById(orderItemDTO.getId())
                .orElseThrow(() -> new OrderItemNotFoundException("Order Item Not Found after insertion"));
    }

    @Override
    @Transactional
    public OrderItemDTO updateOrderItem(OrderItemDTO orderItemDTO) {

        validateOrderItemDTO(orderItemDTO);

        boolean result = orderItemRepository.updateOrderItem(orderItemDTO);
        if (!result) {
            logger.error("Failed to update order item with id: {}", orderItemDTO.getId());
            throw new OrderItemNotFoundException("Failed to update order item with id: " + orderItemDTO.getId());
        }
        logger.info("Order Item [{}] updated successfully", orderItemDTO.getId());

        return orderItemRepository.selectOrderItemById(orderItemDTO.getId())
                .orElseThrow(() -> new OrderItemNotFoundException("Order Item Not Found after update"));
    }

    @Override
    @Transactional
    public void deleteOrderItemById(String id) {
        validateId(id);
        boolean result = orderItemRepository.deleteOrderItemById(id);
        if (!result) {
            logger.error("Failed to delete order item with id: {}", id);
            throw new OrderItemNotFoundException("Failed to delete order item with id: " + id);
        }
        logger.info("Order Item [{}] deleted successfully", id);
    }

    private void validateId(String id) {
        if (Objects.isNull(id) || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Order Item ID cannot be null or empty");
        }
    }

    private void validateOrderItemDTO(OrderItemDTO orderItemDTO) {
        Set<ConstraintViolation<OrderItemDTO>> violations = validator.validate(orderItemDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation failed for orderItemDTO", violations);
        }
    }

    private void validateOrderItemRequestDTO(OrderItemRequestDTO orderItemRequestDTO) {
        Set<ConstraintViolation<OrderItemRequestDTO>> violations = validator.validate(orderItemRequestDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation failed for orderItemRequestDTO", violations);
        }
    }
}
