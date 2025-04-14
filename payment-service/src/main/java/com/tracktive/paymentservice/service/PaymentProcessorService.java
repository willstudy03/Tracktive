package com.tracktive.paymentservice.service;

import com.tracktive.paymentservice.model.DTO.PaymentProcessorRequestDTO;
import com.tracktive.paymentservice.model.DTO.PaymentProcessorResponseDTO;

/**
* Description: Payment Processor Service Interface
* @author William Theo
* @date 14/4/2025
*/
public interface PaymentProcessorService {
    PaymentProcessorResponseDTO initiatePayment(PaymentProcessorRequestDTO paymentProcessorRequestDTO);
}
