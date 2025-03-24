package com.tracktive.paymentservice.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
* Description: Payment Transaction POJO
* @author William Theo
* @date 24/3/2025
*/
public class PaymentTransaction {

    private String id;  // Internal transaction ID

    private String paymentId;  // References the Payment entity

    private String stripePaymentIntentId; // Stores Stripe PaymentIntent ID

    private String stripeChargeId; // Stores Stripe Charge ID

    private String stripePaymentStatus; // Stripe payment status (e.g., succeeded, failed)

    private String receiptUrl; // Stripe receipt URL

    private BigDecimal amount; // Amount paid

    private String currency; // Currency of transaction

    private LocalDateTime stripeCreatedAt; // Time of transaction from Stripe

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;

    public PaymentTransaction() {
    }

    public PaymentTransaction(String id, String paymentId, String stripePaymentIntentId, String stripeChargeId, String stripePaymentStatus, String receiptUrl, BigDecimal amount, String currency, LocalDateTime stripeCreatedAt) {
        this.id = id;
        this.paymentId = paymentId;
        this.stripePaymentIntentId = stripePaymentIntentId;
        this.stripeChargeId = stripeChargeId;
        this.stripePaymentStatus = stripePaymentStatus;
        this.receiptUrl = receiptUrl;
        this.amount = amount;
        this.currency = currency;
        this.stripeCreatedAt = stripeCreatedAt;
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

    public String getStripePaymentIntentId() {
        return stripePaymentIntentId;
    }

    public void setStripePaymentIntentId(String stripePaymentIntentId) {
        this.stripePaymentIntentId = stripePaymentIntentId;
    }

    public String getStripeChargeId() {
        return stripeChargeId;
    }

    public void setStripeChargeId(String stripeChargeId) {
        this.stripeChargeId = stripeChargeId;
    }

    public String getStripePaymentStatus() {
        return stripePaymentStatus;
    }

    public void setStripePaymentStatus(String stripePaymentStatus) {
        this.stripePaymentStatus = stripePaymentStatus;
    }

    public String getReceiptUrl() {
        return receiptUrl;
    }

    public void setReceiptUrl(String receiptUrl) {
        this.receiptUrl = receiptUrl;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDateTime getStripeCreatedAt() {
        return stripeCreatedAt;
    }

    public void setStripeCreatedAt(LocalDateTime stripeCreatedAt) {
        this.stripeCreatedAt = stripeCreatedAt;
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
