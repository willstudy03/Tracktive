package com.tracktive.paymentservice.repository;

import com.tracktive.paymentservice.model.DTO.PaymentTransactionDTO;
import com.tracktive.paymentservice.model.entity.PaymentTransaction;

import java.util.List;
import java.util.Optional;

/**
* Description: Payment Transaction Repository
* @author William Theo
* @date 24/3/2025
*/
public interface PaymentTransactionRepository {
    // Select operations
    List<PaymentTransactionDTO> selectAllPaymentTransactions();

    List<PaymentTransactionDTO> selectAllPaymentTransactionsByPaymentId(String id);

    Optional<PaymentTransactionDTO> selectPaymentTransactionById(String id);

    Optional<PaymentTransactionDTO> selectPaymentTransactionByStripeSessionId(String stripeSessionId);

    // Lock operation
    Optional<PaymentTransactionDTO> lockPaymentTransactionById(String id);

    // Insert operation
    boolean addPaymentTransaction(PaymentTransactionDTO paymentTransactionDTO);

    // Update operation
    boolean updatePaymentTransaction(PaymentTransactionDTO paymentTransactionDTO);

    // Delete operation
    boolean deletePaymentTransactionById(String id);
}
