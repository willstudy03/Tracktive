package com.tracktive.orderservice.model.entity;

import com.tracktive.orderservice.model.Enum.OrderStatus;

import java.math.BigDecimal;

/**
* Description: Order POJO
* @author William Theo
* @date 15/3/2025
*/
public class Order {

    private String id;

    private String retailerId;

    private String supplierId;

    private String paymentId;

    private String deliveryTaskId;

    private BigDecimal totalAmount;

    private String deliveryAddress;

    private OrderStatus orderStatus;

    private String updatedAt;

    private String createdAt;

    public Order() {
    }

    public Order(String id, String retailerId, String supplierId, String paymentId, String deliveryTaskId, BigDecimal totalAmount, String deliveryAddress, OrderStatus orderStatus) {
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

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
