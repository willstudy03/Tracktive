package com.tracktive.orderservice.repository.Impl;

import com.tracktive.orderservice.model.DTO.OrderDTO;
import com.tracktive.orderservice.model.Enum.OrderStatus;
import com.tracktive.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
* Description: Order Repository CRUD Test Case
* @author William Theo
* @date 19/3/2025
*/
@SpringBootTest
class OrderRepositoryImplTest {

    @Autowired
    private OrderRepository orderRepository;

    private static final Logger logger = LoggerFactory.getLogger(OrderRepositoryImplTest.class);

    @Test
    void selectAllOrders() {
        List<OrderDTO> orderDTOS = orderRepository.selectAllOrders();
        logger.info("Orders:{}", orderDTOS);
        assertEquals(orderDTOS.isEmpty(),false);
    }

    @Test
    void selectAllOrdersByRetailerId() {
        String retailerId = "retailer123";
        List<OrderDTO> orderDTOS = orderRepository.selectAllOrdersByRetailerId(retailerId);
        logger.info("Orders:{}", orderDTOS);
        assertEquals(orderDTOS.get(0).getRetailerId(), retailerId);
    }

    @Test
    void selectAllOrdersBySupplierId() {
        String supplierId = "supplier456";   // Replace with actual supplier ID
        List<OrderDTO> orderDTOS = orderRepository.selectAllOrdersBySupplierId(supplierId);
        logger.info("Orders:{}", orderDTOS);
        assertEquals(orderDTOS.get(0).getSupplierId(), supplierId);
    }

    @Test
    void selectOrderById() {
        String orderId = "05d4ff2c-9e03-4f35-9f36-7d9e50c7d0c0";
        Optional<OrderDTO> order = orderRepository.selectOrderById(orderId);
        OrderDTO result = order.get();
        assertEquals(orderId, result.getId());
    }

    @Test
    void lockOrderById() {
        String orderId = "05d4ff2c-9e03-4f35-9f36-7d9e50c7d0c0";
        Optional<OrderDTO> order = orderRepository.lockOrderById(orderId);
        OrderDTO result = order.get();
        assertEquals(orderId, result.getId());
    }

    @Test
    void addOrder() {
        // Generate a random UUID for the order ID
        String orderId = "05d4ff2c-9e03-4f35-9f36-7d9e50c7d0c0";
        String retailerId = "retailer123";   // Replace with actual retailer ID
        String supplierId = "supplier456";   // Replace with actual supplier ID
        String paymentId = "payment789";     // Replace with actual payment ID
        String deliveryTaskId = "delivery001"; // Replace with actual delivery task ID
        BigDecimal totalAmount = new BigDecimal("500.75");
        String deliveryAddress = "123, Test Street, Kuala Lumpur, Malaysia";
        OrderStatus orderStatus = OrderStatus.PENDING;

        // Create the Order object
        OrderDTO order = new OrderDTO(orderId, retailerId, supplierId, paymentId, deliveryTaskId, totalAmount, deliveryAddress, orderStatus);
        boolean result = orderRepository.addOrder(order);
        assertTrue(result);
    }

    @Test
    void updateOrder() {
        Optional<OrderDTO> order = orderRepository.selectOrderById("05d4ff2c-9e03-4f35-9f36-7d9e50c7d0c0");
        OrderDTO updateOrder = order.get();
        logger.info("Order Status:{}", updateOrder.getOrderStatus());
        updateOrder.setOrderStatus(OrderStatus.CANCELLED);
        assertTrue(orderRepository.updateOrder(updateOrder));
    }

    @Test
    void deleteOrderById() {
        String id = "05d4ff2c-9e03-4f35-9f36-7d9e50c7d0c0";
        boolean delResult = orderRepository.deleteOrderById(id);
        assertTrue(delResult);
    }
}