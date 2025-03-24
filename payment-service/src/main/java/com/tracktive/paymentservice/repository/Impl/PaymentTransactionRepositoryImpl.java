package com.tracktive.paymentservice.repository.Impl;

import com.tracktive.paymentservice.exception.DatabaseOperationException;
import com.tracktive.paymentservice.exception.PaymentTransactionAlreadyExistException;
import com.tracktive.paymentservice.model.DAO.PaymentTransactionDAO;
import com.tracktive.paymentservice.model.entity.PaymentTransaction;
import com.tracktive.paymentservice.repository.PaymentTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
* Description: Payment Transaction Repository
* @author William Theo
* @date 24/3/2025
*/
@Repository
public class PaymentTransactionRepositoryImpl implements PaymentTransactionRepository {

    private final PaymentTransactionDAO paymentTransactionDAO;

    @Autowired
    public PaymentTransactionRepositoryImpl(PaymentTransactionDAO paymentTransactionDAO) {
        this.paymentTransactionDAO = paymentTransactionDAO;
    }

    @Override
    public List<PaymentTransaction> selectAllPaymentTransactions() {
        return paymentTransactionDAO.selectAllPaymentTransactions();
    }

    @Override
    public List<PaymentTransaction> selectAllPaymentTransactionsByPaymentId(String id) {
        return paymentTransactionDAO.selectAllPaymentTransactionsByPaymentId(id);
    }

    @Override
    public Optional<PaymentTransaction> selectPaymentTransactionById(String id) {
        return paymentTransactionDAO.selectPaymentTransactionById(id);
    }

    @Override
    public Optional<PaymentTransaction> lockPaymentTransactionById(String id) {
        return paymentTransactionDAO.lockPaymentTransactionById(id);
    }

    @Override
    public boolean addPaymentTransaction(PaymentTransaction paymentTransaction) {
        try {
            return paymentTransactionDAO.addPaymentTransaction(paymentTransaction) > 0;
        } catch (DuplicateKeyException e) {
            throw new PaymentTransactionAlreadyExistException("Payment Transaction with id " + paymentTransaction.getId() + " already exists", e);
        } catch (DataAccessException e) {
            throw new DatabaseOperationException("Failed to add Payment Transaction to the database", e);
        }
    }

    @Override
    public boolean updatePaymentTransaction(PaymentTransaction paymentTransaction) {
        return paymentTransactionDAO.updatePaymentTransaction(paymentTransaction) > 0;
    }

    @Override
    public boolean deletePaymentTransactionById(String id) {
        return paymentTransactionDAO.deletePaymentTransactionById(id) > 0;
    }
}
