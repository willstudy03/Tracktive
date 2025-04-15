package com.tracktive.paymentservice.service;

import com.tracktive.paymentservice.model.DTO.PaymentTransactionDTO;
import com.tracktive.paymentservice.model.DTO.PaymentTransactionRequestDTO;

import java.util.List;

/**
* Description: Payment Transaction Service Interface
* @author William Theo
* @date 24/3/2025
*/
public interface PaymentTransactionService {
    // Select operations
    List<PaymentTransactionDTO> selectAllPaymentTransactions();

    PaymentTransactionDTO selectPaymentTransactionByPaymentId(String id);

    PaymentTransactionDTO selectPaymentTransactionById(String id);

    PaymentTransactionDTO selectPaymentTransactionByStripeSessionId(String stripeSessionId);

    // Lock operation
    PaymentTransactionDTO lockPaymentTransactionById(String id);

    // Insert operation
    PaymentTransactionDTO addPaymentTransaction(PaymentTransactionRequestDTO paymentTransactionRequestDTO);

    // Update operation
    PaymentTransactionDTO updatePaymentTransaction(PaymentTransactionDTO paymentTransactionDTO);

    // Delete operation
    void deletePaymentTransactionById(String id);
}
