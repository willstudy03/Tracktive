package com.tracktive.paymentservice.repository.Impl;

import com.tracktive.paymentservice.model.Enum.PaymentMethod;
import com.tracktive.paymentservice.model.Enum.PaymentStatus;
import com.tracktive.paymentservice.model.entity.Payment;
import com.tracktive.paymentservice.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PaymentRepositoryImplTest {

    @Autowired
    private PaymentRepository paymentRepository;
    private static final Logger logger = LoggerFactory.getLogger(PaymentRepositoryImplTest.class);

    @Test
    void selectAllPayments() {
        List<Payment> paymentDTOS = paymentRepository.selectAllPayments();
        logger.info("Payment:{}", paymentDTOS);
        assertEquals(paymentDTOS.isEmpty(),false);
    }

    @Test
    void selectAllPaymentsByUserId() {
        String id = "user123";
        List<Payment> paymentDTOS = paymentRepository.selectAllPaymentsByUserId(id);
        logger.info("Payment:{}", paymentDTOS);
        assertEquals(paymentDTOS.getFirst().getUserId(), id);
    }

    @Test
    void selectPaymentById() {
        String id = "af9a5e98-a4e4-44bd-8de8-b671a0e2d875";
        Optional<Payment> paymentDTO = paymentRepository.selectPaymentById(id);
        logger.info("Payment:{}", paymentDTO);
        assertEquals(paymentDTO.get().getId(), id);
    }

    @Test
    void lockPaymentById() {
        String id = "af9a5e98-a4e4-44bd-8de8-b671a0e2d875";
        Optional<Payment> paymentDTO = paymentRepository.lockPaymentById(id);
        logger.info("Payment:{}", paymentDTO);
        assertEquals(paymentDTO.get().getId(), id);
    }

    @Test
    void addPayment() {
        Payment payment = new Payment();
        payment.setId("af9a5e98-a4e4-44bd-8de8-b671a0e2d875"); // Generate a unique ID
        payment.setOrderId("977f133c-0a85-438b-83c5-efcf38827ab6"); // Simulate an order ID
        payment.setUserId("user123"); // Example user ID
        payment.setCurrency("MYR"); // Example currency (Malaysian Ringgit)
        payment.setAmount(BigDecimal.valueOf(500.00)); // Example amount
        payment.setTotalPaidAmount(BigDecimal.ZERO); // Initially no payment made
        payment.setPaymentMethod(PaymentMethod.PAY_BY_ONCE);
        payment.setPaymentStatus(PaymentStatus.PENDING); // Assuming `PENDING` is a valid status

        boolean result = paymentRepository.addPayment(payment);
        assertTrue(result);
    }

    @Test
    void updatePayment() {
        Optional<Payment> paymentDTO = paymentRepository.selectPaymentById("af9a5e98-a4e4-44bd-8de8-b671a0e2d875");
        Payment updatePayment = paymentDTO.get();
        logger.info("Payment Status:{}", updatePayment.getPaymentStatus());
        updatePayment.setPaymentStatus(PaymentStatus.COMPLETED);
        assertTrue(paymentRepository.updatePayment(updatePayment));
    }

    @Test
    void deletePaymentById() {
        String id = "af9a5e98-a4e4-44bd-8de8-b671a0e2d875";
        boolean delResult = paymentRepository.deletePaymentById(id);
        assertTrue(delResult);
    }
}