package com.tracktive.paymentservice.service;

import com.tracktive.paymentservice.model.DTO.PaymentTransactionDTO;

import java.util.List;

/**
* Description: Payment Transaction Service Interface
* @author William Theo
* @date 24/3/2025
*/
public interface PaymentTransactionService {
    // Select operations
    List<PaymentTransactionDTO> selectAllPaymentTransactions();

    List<PaymentTransactionDTO> selectAllPaymentTransactionsByPaymentId(String id);

    PaymentTransactionDTO selectPaymentTransactionById(String id);

    // Lock operation
    PaymentTransactionDTO lockPaymentTransactionById(String id);

    // Insert operation
    void addPaymentTransaction(PaymentTransactionDTO paymentTransactionDTO);

    // Update operation
    void updatePaymentTransaction(PaymentTransactionDTO paymentTransactionDTO);

    // Delete operation
    void deletePaymentTransactionById(String id);
}
