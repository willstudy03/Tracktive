package com.tracktive.paymentservice.model.DTO;

import java.math.BigDecimal;

/**
* Description:
* @author William Theo
* @date 15/4/2025
*/
public class PaymentTransactionRequestDTO {

    private String paymentId;

    private String stripeSessionId;

    private String currency;

    private BigDecimal amount;

    public PaymentTransactionRequestDTO() {
    }

    public PaymentTransactionRequestDTO(String paymentId, String stripeSessionId, String currency, BigDecimal amount) {
        this.paymentId = paymentId;
        this.stripeSessionId = stripeSessionId;
        this.currency = currency;
        this.amount = amount;
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

}



