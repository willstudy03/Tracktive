package com.tracktive.orderservice.model.DTO;

import com.tracktive.orderservice.model.Enum.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
* Description: Order DTO
* @author William Theo
* @date 16/3/2025
*/
public class OrderDTO {

    private String id;

    private String retailerId;

    private String supplierId;

    private String paymentId;

    private String deliveryTaskId;

    private BigDecimal totalAmount;

    private String deliveryAddress;

    private OrderStatus orderStatus;

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;

    public OrderDTO() {
    }

    public OrderDTO(String id, String retailerId, String supplierId, String paymentId, String deliveryTaskId, BigDecimal totalAmount, String deliveryAddress, OrderStatus orderStatus) {
        this.id = id;
        this.retailerId = retailerId;
        this.supplierId = supplierId;
        this.paymentId = paymentId;
        this.deliveryTaskId = deliveryTaskId;
        this.totalAmount = totalAmount;
        this.deliveryAddress = deliveryAddress;
        this.orderStatus = orderStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(String retailerId) {
        this.retailerId = retailerId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getDeliveryTaskId() {
        return deliveryTaskId;
    }

    public void setDeliveryTaskId(String deliveryTaskId) {
        this.deliveryTaskId = deliveryTaskId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
