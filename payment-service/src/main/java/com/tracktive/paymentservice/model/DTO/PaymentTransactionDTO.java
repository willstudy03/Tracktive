package com.tracktive.paymentservice.model.DTO;

import com.tracktive.paymentservice.model.Enum.PaymentStatus;
import com.tracktive.paymentservice.model.Enum.StripePaymentStatus;
import com.tracktive.paymentservice.util.annotation.ValidEnum;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
* Description: Payment Transaction DTO
* @author William Theo
* @date 24/3/2025
*/
public class PaymentTransactionDTO {

    @NotBlank(message = "Payment Transaction ID is required")
    private String id;

    @NotBlank(message = "Payment ID is required")
    private String paymentId;

    @NotBlank(message = "Stripe Session ID is required")
    private String stripeSessionId;

    @NotBlank(message = "Currency is required")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Invalid currency format (Use ISO 4217, e.g., USD, MYR, EUR)")
    private String currency;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private BigDecimal amount;

    @NotBlank(message = "Stripe Session URL is required")
    private String sessionUrl;

    @NotNull(message = "Stripe Payment Status is required")
    @ValidEnum(enumClass = StripePaymentStatus.class, message = "Invalid Stripe Payment Status")
    private StripePaymentStatus stripePaymentStatus;

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;

    public PaymentTransactionDTO() {
    }

    public PaymentTransactionDTO(String id, String paymentId, String stripeSessionId, String currency, BigDecimal amount, String sessionUrl, StripePaymentStatus stripePaymentStatus) {
        this.id = id;
        this.paymentId = paymentId;
        this.stripeSessionId = stripeSessionId;
        this.currency = currency;
        this.amount = amount;
        this.sessionUrl = sessionUrl;
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

    public String getSessionUrl() {
        return sessionUrl;
    }

    public void setSessionUrl(String sessionUrl) {
        this.sessionUrl = sessionUrl;
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
