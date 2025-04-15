package com.tracktive.paymentservice.model.DTO;

import com.tracktive.paymentservice.model.Enum.StripePaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
* Description: Payment Transaction DTO
* @author William Theo
* @date 24/3/2025
*/
public class PaymentTransactionDTO {

    private String id;

    private String paymentId;

    private String stripeSessionId;

    private String currency;

    private BigDecimal amount;

    private StripePaymentStatus stripePaymentStatus;

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;

    public PaymentTransactionDTO() {
    }

    public PaymentTransactionDTO(String id, String paymentId, String stripeSessionId, String currency, BigDecimal amount, StripePaymentStatus stripePaymentStatus) {
        this.id = id;
        this.paymentId = paymentId;
        this.stripeSessionId = stripeSessionId;
        this.currency = currency;
        this.amount = amount;
        this.stripePaymentStatus = stripePaymentStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getStripeSessionId() {
        return stripeSessionId;
    }

    public void setStripeSessionId(String stripeSessionId) {
        this.stripeSessionId = stripeSessionId;
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

    public StripePaymentStatus getStripePaymentStatus() {
        return stripePaymentStatus;
    }

    public void setStripePaymentStatus(StripePaymentStatus stripePaymentStatus) {
        this.stripePaymentStatus = stripePaymentStatus;
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
