package com.tracktive.paymentservice.service.Impl;

import com.tracktive.paymentservice.exception.LockAcquisitionException;
import com.tracktive.paymentservice.exception.PaymentNotFoundException;
import com.tracktive.paymentservice.model.DTO.PaymentDTO;
import com.tracktive.paymentservice.model.DTO.PaymentRequestDTO;
import com.tracktive.paymentservice.repository.PaymentRepository;
import com.tracktive.paymentservice.service.PaymentService;
import com.tracktive.paymentservice.util.converter.PaymentConverter;
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
* Description: Payment Service Implementation
* @author William Theo
* @date 24/3/2025
*/
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final Validator validator;

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, Validator validator) {
        this.paymentRepository = paymentRepository;
        this.validator = validator;
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
    public PaymentDTO addPayment(PaymentRequestDTO paymentRequestDTO) {

        validatePaymentRequestDTO(paymentRequestDTO);

        PaymentDTO paymentDTO = PaymentConverter.toDTO(paymentRequestDTO);

        boolean result = paymentRepository.addPayment(paymentDTO);
        if (!result) {
            logger.error("Failed to add payment with id: {}", paymentDTO.getId());
            throw new RuntimeException("Failed to add payment with id: " + paymentDTO.getId());
        }
        logger.info("Payment [{}] added successfully", paymentDTO.getId());

        return paymentRepository.selectPaymentById(paymentDTO.getId())
                .orElseThrow(() -> new PaymentNotFoundException("Failed to fetch payment after insertion"));
    }

    @Override
    @Transactional
    public PaymentDTO updatePayment(PaymentDTO paymentDTO) {

        validatePaymentDTO(paymentDTO);

        boolean result = paymentRepository.updatePayment(paymentDTO);
        if (!result) {
            logger.error("Failed to update payment with id: {}", paymentDTO.getId());
            throw new PaymentNotFoundException("Failed to update payment with id: " + paymentDTO.getId());
        }
        logger.info("Payment [{}] updated successfully", paymentDTO.getId());

        return paymentRepository.selectPaymentById(paymentDTO.getId())
                .orElseThrow(() -> new PaymentNotFoundException("Failed to fetch payment after update"));
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
        Set<ConstraintViolation<PaymentDTO>> violations = validator.validate(paymentDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation failed for paymentDTO", violations);
        }
    }

    private void validatePaymentRequestDTO(PaymentRequestDTO paymentRequestDTO) {
        Set<ConstraintViolation<PaymentRequestDTO>> violations = validator.validate(paymentRequestDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation failed for paymentRequestDTO", violations);
        }
    }
}
