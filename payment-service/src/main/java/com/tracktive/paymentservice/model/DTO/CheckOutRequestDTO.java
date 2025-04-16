package com.tracktive.paymentservice.model.DTO;

/**
* Description: Payment Processor Request DTO
* @author William Theo
* @date 14/4/2025
*/
public class CheckOutRequestDTO {

    String paymentId;

    public CheckOutRequestDTO() {
    }

    public CheckOutRequestDTO(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
}
