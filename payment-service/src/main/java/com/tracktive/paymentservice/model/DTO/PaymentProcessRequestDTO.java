package com.tracktive.paymentservice.model.DTO;

import com.stripe.model.checkout.Session;

/**
* Description: Payment Process Request DTO
* @author William Theo
* @date 16/4/2025
*/
public class PaymentProcessRequestDTO {

    private Session session;

    public PaymentProcessRequestDTO() {
    }

    public PaymentProcessRequestDTO(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
