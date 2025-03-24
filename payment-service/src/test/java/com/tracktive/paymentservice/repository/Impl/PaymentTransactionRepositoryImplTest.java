package com.tracktive.paymentservice.repository.Impl;

import com.tracktive.paymentservice.model.DTO.PaymentDTO;
import com.tracktive.paymentservice.model.Enum.PaymentMethod;
import com.tracktive.paymentservice.model.Enum.PaymentStatus;
import com.tracktive.paymentservice.model.entity.PaymentTransaction;
import com.tracktive.paymentservice.repository.PaymentTransactionRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PaymentTransactionRepositoryImplTest {

    @Autowired
    private PaymentTransactionRepository paymentTransactionRepository;

    private static final Logger logger = LoggerFactory.getLogger(PaymentTransactionRepositoryImplTest.class);

    @Test
    void selectAllPaymentTransactions() {
        List<PaymentTransaction> paymentTransactionDTOS = paymentTransactionRepository.selectAllPaymentTransactions();
        logger.info("Payment Transaction:{}", paymentTransactionDTOS);
        assertEquals(paymentTransactionDTOS.isEmpty(),false);
    }

    @Test
    void selectAllPaymentTransactionsByPaymentId() {
        String paymentID = "17bddb5c-e0ec-4a21-81e2-415242cb86cf";
        List<PaymentTransaction> paymentTransactionDTOS = paymentTransactionRepository.selectAllPaymentTransactionsByPaymentId(paymentID);
        logger.info("Payment Transaction:{}", paymentTransactionDTOS);
        assertEquals(paymentTransactionDTOS.getFirst().getPaymentId(), paymentID);
    }

    @Test
    void selectPaymentTransactionById() {
        String transactionID = "d6da16c1-9924-4633-92bf-41c7529a81d9";
        Optional<PaymentTransaction> paymentTransactionDTOS = paymentTransactionRepository.selectPaymentTransactionById(transactionID);
        logger.info("Payment Transaction:{}", paymentTransactionDTOS);
        assertEquals(paymentTransactionDTOS.get().getId(), transactionID);
    }

    @Test
    void lockPaymentTransactionById() {
        String transactionID = "d6da16c1-9924-4633-92bf-41c7529a81d9";
        Optional<PaymentTransaction> paymentTransactionDTOS = paymentTransactionRepository.lockPaymentTransactionById(transactionID);
        logger.info("Payment Transaction:{}", paymentTransactionDTOS);
        assertEquals(paymentTransactionDTOS.get().getId(), transactionID);
    }

    @Test
    void addPaymentTransaction() {
        PaymentTransaction paymentTransaction = new PaymentTransaction();
        paymentTransaction.setId("d6da16c1-9924-4633-92bf-41c7529a81d9");  // Generate a unique ID
        paymentTransaction.setPaymentId("17bddb5c-e0ec-4a21-81e2-415242cb86cf");  // Reference to Payment entity
        paymentTransaction.setStripePaymentIntentId("pi_3Kx0yf2eZvKYlo2CxJdK2Tx1");
        paymentTransaction.setStripeChargeId("ch_3Kx0yf2eZvKYlo2Cxnz9zMzA");
        paymentTransaction.setStripePaymentStatus("succeeded");
        paymentTransaction.setReceiptUrl("https://pay.stripe.com/receipts/xyz");
        paymentTransaction.setAmount(new BigDecimal("199.99"));
        paymentTransaction.setCurrency("USD");
        paymentTransaction.setStripeCreatedAt(LocalDateTime.now().minusMinutes(5)); // Simulate a past Stripe transaction time

        boolean result = paymentTransactionRepository.addPaymentTransaction(paymentTransaction);
        assertTrue(result);
    }

    @Test
    void updatePaymentTransaction() {
        Optional<PaymentTransaction> paymentTransactionDTO = paymentTransactionRepository.selectPaymentTransactionById("d6da16c1-9924-4633-92bf-41c7529a81d9");
        PaymentTransaction updatePaymentTransaction = paymentTransactionDTO.get();
        logger.info("Payment Transaction Currency:{}", updatePaymentTransaction.getCurrency());
        updatePaymentTransaction.setCurrency("MYR");
        assertTrue(paymentTransactionRepository.updatePaymentTransaction(updatePaymentTransaction));
    }

    @Test
    void deletePaymentTransactionById() {
        String id = "d6da16c1-9924-4633-92bf-41c7529a81d9";
        boolean delResult = paymentTransactionRepository.deletePaymentTransactionById(id);
        assertTrue(delResult);
    }
}