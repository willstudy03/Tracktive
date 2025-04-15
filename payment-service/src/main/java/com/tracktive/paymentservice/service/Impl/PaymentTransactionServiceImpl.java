package com.tracktive.paymentservice.service.Impl;

import com.tracktive.paymentservice.exception.LockAcquisitionException;
import com.tracktive.paymentservice.exception.PaymentNotFoundException;
import com.tracktive.paymentservice.exception.PaymentTransactionNotFoundException;
import com.tracktive.paymentservice.model.DTO.PaymentRequestDTO;
import com.tracktive.paymentservice.model.DTO.PaymentTransactionDTO;
import com.tracktive.paymentservice.model.DTO.PaymentTransactionRequestDTO;
import com.tracktive.paymentservice.repository.PaymentTransactionRepository;
import com.tracktive.paymentservice.service.PaymentTransactionService;
import com.tracktive.paymentservice.util.converter.PaymentTransactionConverter;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
* Description: Payment Transaction Service Implementation
* @author William Theo
* @date 24/3/2025
*/
@Service
public class PaymentTransactionServiceImpl implements PaymentTransactionService {
    
    private final PaymentTransactionRepository paymentTransactionRepository;

    private final Validator validator;

    private static final Logger logger = LoggerFactory.getLogger(PaymentTransactionServiceImpl.class);

    @Autowired
    public PaymentTransactionServiceImpl(PaymentTransactionRepository paymentTransactionRepository, Validator validator) {
        this.paymentTransactionRepository = paymentTransactionRepository;
        this.validator = validator;
    }

    @Override
    public List<PaymentTransactionDTO> selectAllPaymentTransactions() {
        return paymentTransactionRepository.selectAllPaymentTransactions();
    }

    @Override
    public PaymentTransactionDTO selectPaymentTransactionByPaymentId(String id) {
        validatePaymentId(id);
        return paymentTransactionRepository.selectPaymentTransactionByPaymentId(id)
                .orElseThrow(() -> {
                    logger.warn("Payment Transaction not found with payment id: {}", id);
                    return new PaymentTransactionNotFoundException("Payment Transaction not found with payment id: " + id);
                });
    }

    @Override
    public PaymentTransactionDTO selectPaymentTransactionById(String id) {
        validateId(id);
        return paymentTransactionRepository.selectPaymentTransactionById(id)
                .orElseThrow(() -> {
                    logger.warn("Payment Transaction not found with id: {}", id);
                    return new PaymentTransactionNotFoundException("Payment Transaction not found with id: " + id);
                });
    }

    @Override
    public PaymentTransactionDTO selectPaymentTransactionByStripeSessionId(String stripeSessionId) {
        validateStripeSessionId(stripeSessionId);
        return paymentTransactionRepository.selectPaymentTransactionByStripeSessionId(stripeSessionId)
                .orElseThrow(() -> {
                    logger.warn("Payment Transaction not found with stripe session id: {}", stripeSessionId);
                    return new PaymentTransactionNotFoundException("Payment Transaction not found with stripe session id: " + stripeSessionId);
                });
    }

    @Override
    @Transactional
    public PaymentTransactionDTO lockPaymentTransactionById(String id) {
        validateId(id);
        try {
            return paymentTransactionRepository.lockPaymentTransactionById(id)
                    .orElseThrow(() -> {
                        logger.warn("Failed to lock payment transaction, payment transaction not found with id: {}", id);
                        return new PaymentTransactionNotFoundException("Payment transaction not found with id: " + id);
                    });
        } catch (PersistenceException e) {
            logger.error("Persistence error occurred during lock acquisition for payment transaction id: {}", id, e);
            throw new LockAcquisitionException("Failed to acquire lock for payment transaction with id: " + id, e);
        } catch (Exception e) {
            logger.error("Unexpected error during payment transaction lock for id: {}", id, e);
            throw new RuntimeException("Unexpected error during lock operation", e);
        }
    }

    @Override
    @Transactional
    public PaymentTransactionDTO addPaymentTransaction(PaymentTransactionRequestDTO paymentTransactionRequestDTO) {

        PaymentTransactionDTO paymentTransactionDTO = PaymentTransactionConverter.toDTO(paymentTransactionRequestDTO);

        validatePaymentTransactionDTO(paymentTransactionDTO);

        boolean result = paymentTransactionRepository.addPaymentTransaction(paymentTransactionDTO);
        if (!result) {
            logger.error("Failed to add payment transaction with id: {}", paymentTransactionDTO.getId());
            throw new RuntimeException("Failed to add payment transaction with id: " + paymentTransactionDTO.getId());
        }
        logger.info("Payment Transaction [{}] added successfully", paymentTransactionDTO.getId());

        return paymentTransactionRepository.selectPaymentTransactionById(paymentTransactionDTO.getId())
                .orElseThrow(() -> new PaymentTransactionNotFoundException("Failed to fetch payment transaction after insertion"));
    }

    @Override
    @Transactional
    public PaymentTransactionDTO updatePaymentTransaction(PaymentTransactionDTO paymentTransactionDTO) {
        validatePaymentTransactionDTO(paymentTransactionDTO);
        boolean result = paymentTransactionRepository.updatePaymentTransaction(paymentTransactionDTO);
        if (!result) {
            logger.error("Failed to update payment transaction with id: {}", paymentTransactionDTO.getId());
            throw new PaymentTransactionNotFoundException("Failed to update payment transaction with id: " + paymentTransactionDTO.getId());
        }
        logger.info("Payment Transaction [{}] updated successfully", paymentTransactionDTO.getId());

        return paymentTransactionRepository.selectPaymentTransactionById(paymentTransactionDTO.getId())
                .orElseThrow(() -> new PaymentTransactionNotFoundException("Failed to fetch payment transaction after update"));
    }

    @Override
    @Transactional
    public void deletePaymentTransactionById(String id) {
        validateId(id);
        boolean result = paymentTransactionRepository.deletePaymentTransactionById(id);
        if (!result) {
            logger.error("Failed to delete payment transaction with id: {}", id);
            throw new PaymentTransactionNotFoundException("Failed to delete payment transaction with id: " + id);
        }
        logger.info("Payment Transaction [{}] deleted successfully", id);
    }

    private void validatePaymentId(String id) {
        if (Objects.isNull(id) || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Payment ID cannot be null or empty");
        }
    }

    private void validateId(String id) {
        if (Objects.isNull(id) || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Payment Transaction ID cannot be null or empty");
        }
    }

    private void validateStripeSessionId(String stripeSessionId) {
        if (Objects.isNull(stripeSessionId) || stripeSessionId.trim().isEmpty()) {
            throw new IllegalArgumentException("Stipe Session ID cannot be null or empty");
        }
    }

    private void validatePaymentTransactionDTO(PaymentTransactionDTO paymentTransactionDTO) {
        Set<ConstraintViolation<PaymentTransactionDTO>> violations = validator.validate(paymentTransactionDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation failed for paymentTransactionDTO", violations);
        }
    }
}
