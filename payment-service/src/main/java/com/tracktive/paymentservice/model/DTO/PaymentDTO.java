package com.tracktive.paymentservice.model.DTO;

import com.tracktive.paymentservice.model.Enum.PaymentMethod;
import com.tracktive.paymentservice.model.Enum.PaymentStatus;
import com.tracktive.paymentservice.util.annotation.ValidEnum;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
* Description: Payment DTO
* @author William Theo
* @date 24/3/2025
*/
public class PaymentDTO {

    private String id;

    @NotBlank(message = "Order ID is required")
    private String orderId;

    @NotBlank(message = "User ID is required")
    private String userId;

    @NotBlank(message = "Currency is required")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Invalid currency format (Use ISO 4217, e.g., USD, MYR, EUR)")
    private String currency;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private BigDecimal amount;

    @NotNull(message = "Total Paid Amount is required")
    @DecimalMin(value = "0.00", message = "Total Paid Amount must be at least 0")
    private BigDecimal totalPaidAmount;

    private PaymentMethod paymentMethod;

    @NotNull(message = "Payment Status is required")
    @ValidEnum(enumClass = PaymentStatus.class, message = "Invalid Payment Status")
    private PaymentStatus paymentStatus;

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;

    public PaymentDTO() {
    }

    public PaymentDTO(String id, String orderId, String userId, String currency, BigDecimal amount, BigDecimal totalPaidAmount, PaymentMethod paymentMethod, PaymentStatus paymentStatus) {
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
