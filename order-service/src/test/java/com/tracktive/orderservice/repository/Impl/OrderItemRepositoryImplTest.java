package com.tracktive.orderservice.repository.Impl;

import com.tracktive.orderservice.model.DTO.OrderItemDTO;
import com.tracktive.orderservice.model.entity.OrderItem;
import com.tracktive.orderservice.repository.OrderItemRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class OrderItemRepositoryImplTest {

    @Autowired
    private OrderItemRepository orderItemRepository;
    private static final Logger logger = LoggerFactory.getLogger(OrderRepositoryImplTest.class);

    @Test
    void selectAllOrderItems() {
        List<OrderItemDTO> orderItems = orderItemRepository.selectAllOrderItems();
        logger.info("Orders:{}", orderItems);
        assertEquals(orderItems.isEmpty(),false);
    }

    @Test
    void selectOrderItemById() {
        String id = "dda7df47-a091-467c-a490-0222f39f8be2";
        Optional<OrderItemDTO> orderItem = orderItemRepository.selectOrderItemById(id);
        logger.info("Orders:{}", orderItem);
        assertEquals(orderItem.get().getId(), id);
    }

    @Test
    void lockOrderItemById() {
        String id = "dda7df47-a091-467c-a490-0222f39f8be2";
        Optional<OrderItemDTO> orderItem = orderItemRepository.lockOrderItemById(id);
        logger.info("Orders:{}", orderItem);
        assertEquals(orderItem.get().getId(), id);
    }

    @Test
    void addOrderItem() {
        OrderItemDTO orderItemDTO = new OrderItemDTO();

        orderItemDTO.setId("dda7df47-a091-467c-a490-0222f39f8be2");
        orderItemDTO.setOrderId("O5001");
        orderItemDTO.setSupplierProductId("SP2001");
        orderItemDTO.setSupplierId("S3001");
        orderItemDTO.setProductId("P4001");
        orderItemDTO.setQuantity(10);
        orderItemDTO.setPriceSnapshot(new BigDecimal("150.50"));
        orderItemDTO.setDiscountSnapshot(new BigDecimal("10.00"));
        orderItemDTO.setSubtotal(new BigDecimal("1405.00"));

        boolean result = orderItemRepository.addOrderItem(orderItemDTO);
        assertTrue(result);
    }

    @Test
    void updateOrderItem() {
        String id = "dda7df47-a091-467c-a490-0222f39f8be2";
        Optional<OrderItemDTO> orderItem = orderItemRepository.selectOrderItemById(id);
        OrderItemDTO updateOrderItem = orderItem.get();
        logger.info("CartItem price:{}", updateOrderItem.getPriceSnapshot());
        updateOrderItem.setPriceSnapshot(BigDecimal.valueOf(2323.23));
        assertTrue(orderItemRepository.updateOrderItem(updateOrderItem));
    }

    @Test
    void deleteOrderItemById() {
        String id = "dda7df47-a091-467c-a490-0222f39f8be2";
        boolean delResult = orderItemRepository.deleteOrderItemById(id);
        assertTrue(delResult);
    }
}