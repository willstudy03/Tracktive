package com.tracktive.paymentservice.service;

import com.tracktive.paymentservice.model.DTO.PaymentProcessRequestDTO;

/**
* Description: Payment Process Service Interface
* @author William Theo
* @date 16/4/2025
*/
public interface PaymentProcessService {
    void processPaymentSuccess(PaymentProcessRequestDTO paymentProcessRequestDTO);

    void processPaymentFailed(PaymentProcessRequestDTO paymentProcessRequestDTO);
}
