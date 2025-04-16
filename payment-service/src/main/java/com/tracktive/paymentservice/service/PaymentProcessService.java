package com.tracktive.paymentservice.service;

import com.tracktive.paymentservice.model.DTO.PaymentProcessRequestDTO;
import com.tracktive.paymentservice.model.DTO.PaymentProcessResponseDTO;

/**
* Description: Payment Process Service Interface
* @author William Theo
* @date 16/4/2025
*/
public interface PaymentProcessService {
    PaymentProcessResponseDTO processPaymentSuccess(PaymentProcessRequestDTO paymentProcessRequestDTO);
}
