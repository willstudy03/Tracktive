package com.tracktive.orderservice.repository;

import com.tracktive.orderservice.model.Enum.OrderStatus;
import com.tracktive.orderservice.model.entity.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderRepositoryImplTest {

    @Autowired
    private OrderRepository orderRepository;

    private static final Logger logger = LoggerFactory.getLogger(OrderRepositoryImplTest.class);

    @Test
    void selectAllOrders() {
        List<Order> orderDTOS = orderRepository.selectAllOrders();
        logger.info("Orders:{}", orderDTOS);
        assertEquals(orderDTOS.isEmpty(),false);
    }

    @Test
    void selectAllOrdersByRetailerId() {
        String retailerId = "retailer123";
        List<Order> orderDTOS = orderRepository.selectAllOrdersByRetailerId(retailerId);
        logger.info("Orders:{}", orderDTOS);
        assertEquals(orderDTOS.get(0).getRetailerId(), retailerId);
    }

    @Test
    void selectAllOrdersBySupplierId() {
        String supplierId = "supplier456";   // Replace with actual supplier ID
        List<Order> orderDTOS = orderRepository.selectAllOrdersBySupplierId(supplierId);
        logger.info("Orders:{}", orderDTOS);
        assertEquals(orderDTOS.get(0).getSupplierId(), supplierId);
    }

    @Test
    void selectOrderById() {
        String orderId = "05d4ff2c-9e03-4f35-9f36-7d9e50c7d0c0";
        Optional<Order> order = orderRepository.selectOrderById(orderId);
        Order result = order.get();
        assertEquals(orderId, result.getId());
    }

    @Test
    void lockOrderById() {
        String orderId = "05d4ff2c-9e03-4f35-9f36-7d9e50c7d0c0";
        Optional<Order> order = orderRepository.lockOrderById(orderId);
        Order result = order.get();
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
        Order order = new Order(orderId, retailerId, supplierId, paymentId, deliveryTaskId, totalAmount, deliveryAddress, orderStatus);
        boolean result = orderRepository.addOrder(order);
        assertTrue(result);
    }

    @Test
    void updateOrder() {
        Optional<Order> order = orderRepository.selectOrderById("05d4ff2c-9e03-4f35-9f36-7d9e50c7d0c0");
        Order updateOrder = order.get();
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