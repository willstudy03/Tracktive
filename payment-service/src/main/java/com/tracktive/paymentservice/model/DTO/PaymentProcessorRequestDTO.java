package com.tracktive.paymentservice.model.DTO;

/**
* Description: Payment Processor Request DTO
* @author William Theo
* @date 14/4/2025
*/
public class PaymentProcessorRequestDTO {

    String paymentId;

    public PaymentProcessorRequestDTO() {
    }

    public PaymentProcessorRequestDTO(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
}
