package com.tracktive.paymentservice.model.DTO;

import java.math.BigDecimal;
/**
* Description: Stripe Intent Request DTO
* @author William Theo
* @date 13/4/2025
*/
public class StripeIntentRequestDTO {

    BigDecimal amount;

    String currency;

    String description;

    public StripeIntentRequestDTO() {
    }

    public StripeIntentRequestDTO(BigDecimal amount, String currency, String description) {
        this.amount = amount;
        this.currency = currency;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
