package com.tracktive.orderservice.util;

import com.tracktive.orderservice.model.DTO.OrderItemDTO;
import com.tracktive.orderservice.model.entity.OrderItem;

import java.util.Objects;

/**
 * Description: Util for convert Order Item Model
 * @author William Theo
 * @date 19/3/2025
 */
public class OrderItemConverter {

    // Private constructor to prevent instantiation
    private OrderItemConverter() {
    }

    public static OrderItemDTO toDTO(OrderItem orderItem) {
        if (Objects.isNull(orderItem)) {
            return null;
        }
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setId(orderItem.getId());
        orderItemDTO.setOrderId(orderItem.getOrderId());
        orderItemDTO.setSupplierProductId(orderItem.getSupplierProductId());
        orderItemDTO.setSupplierId(orderItem.getSupplierId());
        orderItemDTO.setProductId(orderItem.getProductId());
        orderItemDTO.setQuantity(orderItem.getQuantity());
        orderItemDTO.setPriceSnapshot(orderItem.getPriceSnapshot());
        orderItemDTO.setDiscountSnapshot(orderItem.getDiscountSnapshot());
        orderItemDTO.setSubtotal(orderItem.getSubtotal());
        orderItemDTO.setUpdatedAt(orderItem.getUpdatedAt());
        orderItemDTO.setCreatedAt(orderItem.getCreatedAt());
        return orderItemDTO;
    }

    public static OrderItem toEntity(OrderItemDTO orderItemDTO) {
        if (Objects.isNull(orderItemDTO)) {
            return null;
        }
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemDTO.getId());
        orderItem.setOrderId(orderItemDTO.getOrderId());
        orderItem.setSupplierProductId(orderItemDTO.getSupplierProductId());
        orderItem.setSupplierId(orderItemDTO.getSupplierId());
        orderItem.setProductId(orderItemDTO.getProductId());
        orderItem.setQuantity(orderItemDTO.getQuantity());
        orderItem.setPriceSnapshot(orderItemDTO.getPriceSnapshot());
        orderItem.setDiscountSnapshot(orderItemDTO.getDiscountSnapshot());
        orderItem.setSubtotal(orderItemDTO.getSubtotal());
        orderItem.setUpdatedAt(orderItemDTO.getUpdatedAt());
        orderItem.setCreatedAt(orderItemDTO.getCreatedAt());
        return orderItem;
    }
}
