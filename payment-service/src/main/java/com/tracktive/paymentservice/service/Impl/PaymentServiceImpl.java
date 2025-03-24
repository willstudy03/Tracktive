package com.tracktive.paymentservice.service.Impl;

import com.tracktive.paymentservice.exception.LockAcquisitionException;
import com.tracktive.paymentservice.exception.PaymentNotFoundException;
import com.tracktive.paymentservice.model.DTO.PaymentDTO;
import com.tracktive.paymentservice.repository.PaymentRepository;
import com.tracktive.paymentservice.service.PaymentService;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
* Description: Payment Service Implementation
* @author William Theo
* @date 24/3/2025
*/
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<PaymentDTO> selectAllPayments() {
        return paymentRepository.selectAllPayments();
    }

    @Override
    public List<PaymentDTO> selectAllPaymentsByUserId(String id) {
        validateUserId(id);
        return paymentRepository.selectAllPaymentsByUserId(id);
    }

    @Override
    public PaymentDTO selectPaymentById(String id) {
        validateId(id);
        return paymentRepository.selectPaymentById(id)
                .orElseThrow(() -> {
                    logger.warn("Payment not found with id: {}", id);
                    return new PaymentNotFoundException("Payment not found with id: " + id);
                });
    }

    @Override
    @Transactional
    public PaymentDTO lockPaymentById(String id) {
        validateId(id);
        try {
            return paymentRepository.lockPaymentById(id)
                    .orElseThrow(() -> {
                        logger.warn("Failed to lock payment, payment not found with id: {}", id);
                        return new PaymentNotFoundException("Payment not found with id: " + id);
                    });
        } catch (PersistenceException e) {
            logger.error("Persistence error occurred during lock acquisition for payment id: {}", id, e);
            throw new LockAcquisitionException("Failed to acquire lock for payment with id: " + id, e);
        } catch (Exception e) {
            logger.error("Unexpected error during payment lock for id: {}", id, e);
            throw new RuntimeException("Unexpected error during lock operation", e);
        }
    }

    @Override
    @Transactional
    public void addPayment(PaymentDTO paymentDTO) {
        validatePaymentDTO(paymentDTO);
        boolean result = paymentRepository.addPayment(paymentDTO);
        if (!result) {
            logger.error("Failed to add payment with id: {}", paymentDTO.getId());
            throw new RuntimeException("Failed to add payment with id: " + paymentDTO.getId());
        }
        logger.info("Payment [{}] added successfully", paymentDTO.getId());
    }

    @Override
    @Transactional
    public void updatePayment(PaymentDTO paymentDTO) {
        validatePaymentDTO(paymentDTO);
        boolean result = paymentRepository.updatePayment(paymentDTO);
        if (!result) {
            logger.error("Failed to update payment with id: {}", paymentDTO.getId());
            throw new PaymentNotFoundException("Failed to update payment with id: " + paymentDTO.getId());
        }
        logger.info("Payment [{}] updated successfully", paymentDTO.getId());
    }

    @Override
    @Transactional
    public void deletePaymentById(String id) {
        validateId(id);
        boolean result = paymentRepository.deletePaymentById(id);
        if (!result) {
            logger.error("Failed to delete payment with id: {}", id);
            throw new PaymentNotFoundException("Failed to delete payment with id: " + id);
        }
        logger.info("Payment [{}] deleted successfully", id);
    }

    private void validateUserId(String id) {
        if (Objects.isNull(id) || id.trim().isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
    }

    private void validateId(String id) {
        if (Objects.isNull(id) || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Payment ID cannot be null or empty");
        }
    }

    private void validatePaymentDTO(PaymentDTO paymentDTO) {
        if (Objects.isNull(paymentDTO)) {
            throw new IllegalArgumentException("paymentDTO cannot be null");
        }
        //@TODO: PARAMETERS VALIDATION
    }
}
