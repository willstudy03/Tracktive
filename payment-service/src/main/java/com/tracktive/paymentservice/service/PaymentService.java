package com.tracktive.paymentservice.service;

import com.tracktive.paymentservice.model.DTO.PaymentDTO;
import com.tracktive.paymentservice.model.DTO.PaymentRequestDTO;

import java.util.List;

/**
* Description: Payment Service Interface
* @author William Theo
* @date 24/3/2025
*/
public interface PaymentService {
    // Select operations
    List<PaymentDTO> selectAllPayments();

    List<PaymentDTO> selectAllPaymentsByUserId(String id);

    PaymentDTO selectPaymentById(String id);

    // Lock operation
    PaymentDTO lockPaymentById(String id);

    // Insert operation
    PaymentDTO addPayment(PaymentRequestDTO paymentRequestDTO);

    // Update operation
    PaymentDTO updatePayment(PaymentDTO paymentDTO);

    // Delete operation
    void deletePaymentById(String id);
}
