package com.tracktive.paymentservice.repository.Impl;

import com.tracktive.paymentservice.exception.DatabaseOperationException;
import com.tracktive.paymentservice.exception.PaymentAlreadyExistException;
import com.tracktive.paymentservice.model.DAO.PaymentDAO;
import com.tracktive.paymentservice.model.DTO.PaymentDTO;
import com.tracktive.paymentservice.model.entity.Payment;
import com.tracktive.paymentservice.repository.PaymentRepository;
import com.tracktive.paymentservice.util.PaymentConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
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
    public List<PaymentDTO> selectAllPayments() {
        return paymentDAO.selectAllPayments()
                .stream()
                .map(PaymentConverter::toDTO)
                .toList();
    }

    @Override
    public List<PaymentDTO> selectAllPaymentsByUserId(String id) {
        return paymentDAO.selectAllPaymentsByUserId(id)
                .stream()
                .map(PaymentConverter::toDTO)
                .toList();
    }

    @Override
    public Optional<PaymentDTO> selectPaymentById(String id) {
        return paymentDAO.selectPaymentById(id).map(PaymentConverter::toDTO);
    }

    @Override
    public Optional<PaymentDTO> lockPaymentById(String id) {
        return paymentDAO.lockPaymentById(id).map(PaymentConverter::toDTO);
    }

    @Override
    public boolean addPayment(PaymentDTO paymentDTO) {
        try {
            Payment payment = PaymentConverter.toEntity(paymentDTO);
            return paymentDAO.addPayment(payment) > 0;
        } catch (DuplicateKeyException e) {
            throw new PaymentAlreadyExistException("Payment with id " + paymentDTO.getId() + " already exists", e);
        } catch (DataAccessException e) {
            throw new DatabaseOperationException("Failed to add Payment to the database", e);
        }
    }

    @Override
    public boolean updatePayment(PaymentDTO paymentDTO) {
        Payment payment = PaymentConverter.toEntity(paymentDTO);
        return paymentDAO.updatePayment(payment) > 0;
    }

    @Override
    public boolean deletePaymentById(String id) {
        return paymentDAO.deletePaymentById(id) > 0;
    }
}
