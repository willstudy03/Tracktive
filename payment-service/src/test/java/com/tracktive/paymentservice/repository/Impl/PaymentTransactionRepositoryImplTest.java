package com.tracktive.paymentservice.repository.Impl;

import com.tracktive.paymentservice.model.DTO.PaymentTransactionDTO;
import com.tracktive.paymentservice.model.Enum.StripePaymentStatus;
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
        Optional<PaymentTransactionDTO> paymentTransactionDTO = paymentTransactionRepository.selectPaymentTransactionByPaymentId(paymentID);
        logger.info("Payment Transaction:{}", paymentTransactionDTO);
        assertEquals(paymentTransactionDTO.get().getPaymentId(), paymentID);
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
        paymentTransactionDTO.setStripePaymentStatus(StripePaymentStatus.PENDING); // Updated to status
        paymentTransactionDTO.setAmount(new BigDecimal("199.99"));
        paymentTransactionDTO.setSessionUrl("https://checkout.stripe.com/c/pay/cs_test_a13tSCn2YoIpQnDuvoiXVAtMEWI9sa8SvFCCKJ3WEhdzXOoeu9OVBXc9AU#fidkdWxOYHwnPyd1blpxYHZxWjA0V0ZzdkBBNkh3fHN9MldWR2I1aXBXU3BOMmt1Z0FdamtmQlJQcndBZ2RkVml3clZcZE1dcGlOSzdJc31WY18zSGpDZlQ2ZGRJb0oxQ2xwSndyN2cySFZDNTVfNl9GbjAxfycpJ2N3amhWYHdzYHcnP3F3cGApJ2lkfGpwcVF8dWAnPyd2bGtiaWBabHFgaCcpJ2BrZGdpYFVpZGZgbWppYWB3dic%2FcXdwYHgl");
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
        updatePaymentTransaction.setStripePaymentStatus(StripePaymentStatus.SUCCEEDED);
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