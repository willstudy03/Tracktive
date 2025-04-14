package com.tracktive.paymentservice.model.DTO;

/**
* Description: Payment Processor Request DTO
* @author William Theo
* @date 14/4/2025
*/
public class PaymentProcessorRequestDTO {

    String paymentID;

    public PaymentProcessorRequestDTO() {
    }

    public PaymentProcessorRequestDTO(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }
}
