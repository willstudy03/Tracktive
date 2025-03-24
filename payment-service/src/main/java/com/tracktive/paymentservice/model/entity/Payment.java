package com.tracktive.paymentservice.model.entity;

import com.tracktive.paymentservice.model.Enum.PaymentMethod;
import com.tracktive.paymentservice.model.Enum.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
* Description: Payment POJO
* @author William Theo
* @date 24/3/2025
*/
public class Payment {

    private String id;

    private String orderId;

    private String userId;

    private String currency;

    private BigDecimal amount;

    private BigDecimal totalPaidAmount;

    private PaymentMethod paymentMethod;

    private PaymentStatus paymentStatus;

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;

    public Payment() {
    }

    public Payment(String id, String orderId, String userId, String currency, BigDecimal amount, BigDecimal totalPaidAmount, PaymentMethod paymentMethod, PaymentStatus paymentStatus) {
        this.id = id;
        this.orderId = orderId;
        this.userId = userId;
        this.currency = currency;
        this.amount = amount;
        this.totalPaidAmount = totalPaidAmount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getTotalPaidAmount() {
        return totalPaidAmount;
    }

    public void setTotalPaidAmount(BigDecimal totalPaidAmount) {
        this.totalPaidAmount = totalPaidAmount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
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
