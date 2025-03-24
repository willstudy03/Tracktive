package com.tracktive.paymentservice.repository.Impl;

import com.tracktive.paymentservice.model.DAO.PaymentDAO;
import com.tracktive.paymentservice.model.entity.Payment;
import com.tracktive.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/**
* Description: Payment Repository Implementation
* @author William Theo
* @date 24/3/2025
*/
@Repository
public class PaymentRepositoryImpl implements PaymentRepository {

    private final PaymentDAO paymentDAO;

    @Autowired
    public PaymentRepositoryImpl(PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    @Override
    public List<Payment> selectAllPayments() {
        return paymentDAO.selectAllPayments();
    }

    @Override
    public List<Payment> selectAllPaymentsByUserId(String id) {
        return paymentDAO.selectAllPaymentsByUserId(id);
    }

    @Override
    public Optional<Payment> selectPaymentById(String id) {
        return paymentDAO.selectPaymentById(id);
    }

    @Override
    public Optional<Payment> lockPaymentById(String id) {
        return paymentDAO.lockPaymentById(id);
    }

    @Override
    public boolean addPayment(Payment payment) {
        return paymentDAO.addPayment(payment) > 0;
    }

    @Override
    public boolean updatePayment(Payment payment) {
        return paymentDAO.updatePayment(payment) > 0;
    }

    @Override
    public boolean deletePaymentById(String id) {
        return paymentDAO.deletePaymentById(id) > 0;
    }
}
