package com.tracktive.paymentservice.model.DAO;

import com.tracktive.paymentservice.model.entity.PaymentTransaction;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

/**
* Description: Payment Transaction DAO for mybatis mapping
* @author William Theo
* @date 24/3/2025
*/
@Mapper
public interface PaymentTransactionDAO {
    // Select operations
    List<PaymentTransaction> selectAllPaymentTransactions();

    Optional<PaymentTransaction> selectPaymentTransactionByPaymentId(String id);

    Optional<PaymentTransaction> selectPaymentTransactionById(String id);

    Optional<PaymentTransaction> selectPaymentTransactionByStripeSessionId(String stripeSessionId);

    // Lock operation
    Optional<PaymentTransaction> lockPaymentTransactionById(String id);

    // Insert operation
    int addPaymentTransaction(PaymentTransaction paymentTransaction);

    // Update operation
    int updatePaymentTransaction(PaymentTransaction paymentTransaction);

    // Delete operation
    int deletePaymentTransactionById(String id);
}
