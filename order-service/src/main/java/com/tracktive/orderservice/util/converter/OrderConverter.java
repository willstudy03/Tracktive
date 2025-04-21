package com.tracktive.orderservice.util.converter;

import com.tracktive.orderservice.model.DTO.CartItemDTO;
import com.tracktive.orderservice.model.DTO.OrderPlacementRequestDTO;
import com.tracktive.orderservice.model.DTO.OrderDTO;
import com.tracktive.orderservice.model.DTO.OrderRequestDTO;
import com.tracktive.orderservice.model.Enum.OrderStatus;
import com.tracktive.orderservice.model.entity.Order;
import com.tracktive.orderservice.util.PriceCalculatorUtil;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
* Description: Util for convert Order Model
* @author William Theo
* @date 18/3/2025
*/
public class OrderConverter {

    // Private constructor to prevent instantiation
    private OrderConverter() {
    }

    public static OrderDTO toDTO(Order order) {
        if (Objects.isNull(order)) {
            return null;
        }
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setRetailerId(order.getRetailerId());
        orderDTO.setSupplierId(order.getSupplierId());
        orderDTO.setPaymentId(order.getPaymentId());
        orderDTO.setDeliveryTaskId(order.getDeliveryTaskId());
        orderDTO.setTotalAmount(order.getTotalAmount());
        orderDTO.setDeliveryAddress(order.getDeliveryAddress());
        orderDTO.setOrderStatus(order.getOrderStatus());
        orderDTO.setUpdatedAt(order.getUpdatedAt());
        orderDTO.setCreatedAt(order.getCreatedAt());
        return orderDTO;
    }

    public static OrderDTO toDTO(OrderRequestDTO orderRequestDTO) {
        if (Objects.isNull(orderRequestDTO)) {
            return null;
        }
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(UUID.randomUUID().toString());
        BeanUtils.copyProperties(orderRequestDTO, orderDTO);
        return orderDTO;
    }

    public static Order toEntity(OrderDTO orderDTO) {
        if (Objects.isNull(orderDTO)) {
            return null;
        }
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setRetailerId(orderDTO.getRetailerId());
        order.setSupplierId(orderDTO.getSupplierId());
        order.setPaymentId(orderDTO.getPaymentId());
        order.setDeliveryTaskId(orderDTO.getDeliveryTaskId());
        order.setTotalAmount(orderDTO.getTotalAmount());
        order.setDeliveryAddress(orderDTO.getDeliveryAddress());
        order.setOrderStatus(orderDTO.getOrderStatus());
        order.setUpdatedAt(orderDTO.getUpdatedAt());
        order.setCreatedAt(orderDTO.getCreatedAt());
        return order;
    }

    public static OrderRequestDTO toOrderRequestDTO(OrderPlacementRequestDTO orderPlacementRequestDTO, List<CartItemDTO> cartItems) {
        if (orderPlacementRequestDTO == null || cartItems == null || cartItems.isEmpty()) {
            return null;
        }

        CartItemDTO firstItem = cartItems.get(0);

        OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
        orderRequestDTO.setRetailerId(orderPlacementRequestDTO.getRetailerId());
        orderRequestDTO.setSupplierId(firstItem.getSupplierId());
        orderRequestDTO.setTotalAmount(PriceCalculatorUtil.calculateTotalCartPrice(cartItems));
        orderRequestDTO.setDeliveryAddress(orderPlacementRequestDTO.getDeliveryAddress());
        orderRequestDTO.setOrderStatus(OrderStatus.PENDING);

        return orderRequestDTO;
    }
}
