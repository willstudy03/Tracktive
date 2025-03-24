package com.tracktive.paymentservice.repository;

import com.tracktive.paymentservice.model.entity.Payment;

import java.util.List;
import java.util.Optional;

/**
* Description: Payment Repository Interface
* @author William Theo
* @date 24/3/2025
*/
public interface PaymentRepository {
    // Select operations
    List<Payment> selectAllPayments();

    List<Payment> selectAllPaymentsByUserId(String id);

    Optional<Payment> selectPaymentById(String id);

    // Lock operation
    Optional<Payment> lockPaymentById(String id);

    // Insert operation
    boolean addPayment(Payment payment);

    // Update operation
    boolean updatePayment(Payment payment);

    // Delete operation
    boolean deletePaymentById(String id);
}
