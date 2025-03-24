package com.tracktive.paymentservice.repository;

import com.tracktive.paymentservice.model.DTO.PaymentDTO;

import java.util.List;
import java.util.Optional;

/**
* Description: Payment Repository Interface
* @author William Theo
* @date 24/3/2025
*/
public interface PaymentRepository {
    // Select operations
    List<PaymentDTO> selectAllPayments();

    List<PaymentDTO> selectAllPaymentsByUserId(String id);

    Optional<PaymentDTO> selectPaymentById(String id);

    // Lock operation
    Optional<PaymentDTO> lockPaymentById(String id);

    // Insert operation
    boolean addPayment(PaymentDTO paymentDTO);

    // Update operation
    boolean updatePayment(PaymentDTO paymentDTO);

    // Delete operation
    boolean deletePaymentById(String id);
}
