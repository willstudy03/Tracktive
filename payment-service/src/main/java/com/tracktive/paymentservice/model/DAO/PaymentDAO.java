package com.tracktive.paymentservice.model.DAO;

import com.tracktive.paymentservice.model.entity.Payment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

/**
* Description: Payment DAO for mybatis mapping
* @author William Theo
* @date 24/3/2025
*/
@Mapper
public interface PaymentDAO {
    // Select operations
    List<Payment> selectAllPayments();

    List<Payment> selectAllPaymentsByUserId(String id);

    Optional<Payment> selectPaymentById(String id);

    // Lock operation
    Optional<Payment> lockPaymentById(String id);

    // Insert operation
    int addPayment(Payment payment);

    // Update operation
    int updatePayment(Payment payment);

    // Delete operation
    int deletePaymentById(Payment payment);
}
