package com.tracktive.paymentservice.repository.Impl;

import com.tracktive.paymentservice.exception.DatabaseOperationException;
import com.tracktive.paymentservice.exception.PaymentTransactionAlreadyExistException;
import com.tracktive.paymentservice.model.DAO.PaymentTransactionDAO;
import com.tracktive.paymentservice.model.DTO.PaymentTransactionDTO;
import com.tracktive.paymentservice.model.entity.PaymentTransaction;
import com.tracktive.paymentservice.repository.PaymentTransactionRepository;
import com.tracktive.paymentservice.util.converter.PaymentTransactionConverter;
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
    public List<PaymentTransactionDTO> selectAllPaymentTransactions() {
        return paymentTransactionDAO.selectAllPaymentTransactions()
                .stream()
                .map(PaymentTransactionConverter::toDTO)
                .toList();
    }

    @Override
    public Optional<PaymentTransactionDTO> selectPaymentTransactionByPaymentId(String id) {
        return paymentTransactionDAO.selectPaymentTransactionByPaymentId(id).map(PaymentTransactionConverter::toDTO);
    }

    @Override
    public Optional<PaymentTransactionDTO> selectPaymentTransactionById(String id) {
        return paymentTransactionDAO.selectPaymentTransactionById(id).map(PaymentTransactionConverter::toDTO);
    }

    @Override
    public Optional<PaymentTransactionDTO> selectPaymentTransactionByStripeSessionId(String stripeSessionId) {
        return paymentTransactionDAO.selectPaymentTransactionByStripeSessionId(stripeSessionId).map(PaymentTransactionConverter::toDTO);
    }

    @Override
    public Optional<PaymentTransactionDTO> lockPaymentTransactionById(String id) {
        return paymentTransactionDAO.lockPaymentTransactionById(id).map(PaymentTransactionConverter::toDTO);
    }

    @Override
    public boolean addPaymentTransaction(PaymentTransactionDTO paymentTransactionDTO) {
        try {
            PaymentTransaction paymentTransaction = PaymentTransactionConverter.toEntity(paymentTransactionDTO);
            return paymentTransactionDAO.addPaymentTransaction(paymentTransaction) > 0;
        } catch (DuplicateKeyException e) {
            throw new PaymentTransactionAlreadyExistException("Payment Transaction with id " + paymentTransactionDTO.getId() + " already exists", e);
        } catch (DataAccessException e) {
            throw new DatabaseOperationException("Failed to add Payment Transaction to the database", e);
        }
    }

    @Override
    public boolean updatePaymentTransaction(PaymentTransactionDTO paymentTransactionDTO) {
        PaymentTransaction paymentTransaction = PaymentTransactionConverter.toEntity(paymentTransactionDTO);
        return paymentTransactionDAO.updatePaymentTransaction(paymentTransaction) > 0;
    }

    @Override
    public boolean deletePaymentTransactionById(String id) {
        return paymentTransactionDAO.deletePaymentTransactionById(id) > 0;
    }
}
