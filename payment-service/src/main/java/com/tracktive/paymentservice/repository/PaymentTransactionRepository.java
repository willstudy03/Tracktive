package com.tracktive.paymentservice.repository;

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
    List<PaymentTransaction> selectAllPaymentTransactions();

    List<PaymentTransaction> selectAllPaymentTransactionsByPaymentId(String id);

    Optional<PaymentTransaction> selectPaymentTransactionById(String id);

    // Lock operation
    Optional<PaymentTransaction> lockPaymentTransactionById(String id);

    // Insert operation
    boolean addPaymentTransaction(PaymentTransaction paymentTransaction);

    // Update operation
    boolean updatePaymentTransaction(PaymentTransaction paymentTransaction);

    // Delete operation
    boolean deletePaymentTransactionById(String id);
}
