package com.tracktive.paymentservice.repository.Impl;

import com.tracktive.paymentservice.model.DTO.PaymentTransactionDTO;
import com.tracktive.paymentservice.repository.PaymentTransactionRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class PaymentTransactionRepositoryImplTest {

    @Autowired
    private PaymentTransactionRepository paymentTransactionRepository;

    private static final Logger logger = LoggerFactory.getLogger(PaymentTransactionRepositoryImplTest.class);

    @Test
    void selectAllPaymentTransactions() {
        List<PaymentTransactionDTO> paymentTransactionDTOS = paymentTransactionRepository.selectAllPaymentTransactions();
        logger.info("Payment Transaction:{}", paymentTransactionDTOS);
        assertEquals(paymentTransactionDTOS.isEmpty(),false);
    }

    @Test
    void selectAllPaymentTransactionsByPaymentId() {
        String paymentID = "17bddb5c-e0ec-4a21-81e2-415242cb86cf";
        List<PaymentTransactionDTO> paymentTransactionDTOS = paymentTransactionRepository.selectAllPaymentTransactionsByPaymentId(paymentID);
        logger.info("Payment Transaction:{}", paymentTransactionDTOS);
        assertEquals(paymentTransactionDTOS.getFirst().getPaymentId(), paymentID);
    }

    @Test
    void selectPaymentTransactionById() {
        String transactionID = "d6da16c1-9924-4633-92bf-41c7529a81d9";
        Optional<PaymentTransactionDTO> paymentTransactionDTOS = paymentTransactionRepository.selectPaymentTransactionById(transactionID);
        logger.info("Payment Transaction:{}", paymentTransactionDTOS);
        assertEquals(paymentTransactionDTOS.get().getId(), transactionID);
    }

    @Test
    void lockPaymentTransactionById() {
        String transactionID = "d6da16c1-9924-4633-92bf-41c7529a81d9";
        Optional<PaymentTransactionDTO> paymentTransactionDTOS = paymentTransactionRepository.lockPaymentTransactionById(transactionID);
        logger.info("Payment Transaction:{}", paymentTransactionDTOS);
        assertEquals(paymentTransactionDTOS.get().getId(), transactionID);
    }

    @Test
    void addPaymentTransaction() {
        PaymentTransactionDTO paymentTransactionDTO = new PaymentTransactionDTO();
        paymentTransactionDTO.setId("d6da16c1-9924-4633-92bf-41c7529a81d9");  // Generate a unique ID
        paymentTransactionDTO.setPaymentId("17bddb5c-e0ec-4a21-81e2-415242cb86cf");  // Reference to Payment entity
        paymentTransactionDTO.setStripeSessionId("cs_test_a1B2c3D4e5F6g7H8"); // Updated to stripeSessionId
        paymentTransactionDTO.setStatus("succeeded"); // Updated to status
        paymentTransactionDTO.setAmount(new BigDecimal("199.99"));
        paymentTransactionDTO.setCurrency("USD");

        boolean result = paymentTransactionRepository.addPaymentTransaction(paymentTransactionDTO);
        assertTrue(result);
    }

    @Test
    void updatePaymentTransaction() {
        Optional<PaymentTransactionDTO> paymentTransactionDTO = paymentTransactionRepository.selectPaymentTransactionById("d6da16c1-9924-4633-92bf-41c7529a81d9");
        PaymentTransactionDTO updatePaymentTransaction = paymentTransactionDTO.get();
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

    @Test
    void selectPaymentTransactionByStripeSessionId() {
        String ID = "cs_test_a1B2c3D4e5F6g7H8";
        Optional<PaymentTransactionDTO> paymentTransactionDTOS = paymentTransactionRepository.selectPaymentTransactionByStripeSessionId(ID);
        logger.info("Payment Transaction:{}", paymentTransactionDTOS);
        assertEquals(paymentTransactionDTOS.get().getStripeSessionId(), ID);
    }
}