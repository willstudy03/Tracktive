package com.tracktive.paymentservice.service.Impl;

import com.stripe.model.Event;
import com.tracktive.paymentservice.model.DTO.PaymentDTO;
import com.tracktive.paymentservice.model.DTO.PaymentProcessRequestDTO;
import com.tracktive.paymentservice.model.DTO.PaymentProcessResponseDTO;
import com.tracktive.paymentservice.model.DTO.PaymentTransactionDTO;
import com.tracktive.paymentservice.model.Enum.PaymentStatus;
import com.tracktive.paymentservice.model.Enum.StripePaymentStatus;
import com.tracktive.paymentservice.service.PaymentProcessService;
import com.tracktive.paymentservice.service.PaymentService;
import com.tracktive.paymentservice.service.PaymentTransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* Description: Payment Process Service Implementation
* @author William Theo
* @date 16/4/2025
*/
@Service
public class PaymentProcessServiceImpl implements PaymentProcessService {

    private final PaymentService paymentService;

    private final PaymentTransactionService paymentTransactionService;

    private static final Logger log = LoggerFactory.getLogger(PaymentProcessServiceImpl.class);

    @Autowired
    public PaymentProcessServiceImpl(PaymentService paymentService, PaymentTransactionService paymentTransactionService) {
        this.paymentService = paymentService;
        this.paymentTransactionService = paymentTransactionService;
    }

    @Override
    @Transactional
    public PaymentProcessResponseDTO processPaymentSuccess(PaymentProcessRequestDTO paymentProcessRequestDTO) {

        // First Step: Ensure that the payment transaction is existing

        PaymentTransactionDTO paymentTransactionDTO = paymentTransactionService
                .selectPaymentTransactionByStripeSessionId(paymentProcessRequestDTO.getSession().getId());

        // Lock the payment transaction
        PaymentTransactionDTO lockedPaymentTransaction = paymentTransactionService
                .lockPaymentTransactionById(paymentTransactionDTO.getId());

        // Change the status into success
        lockedPaymentTransaction.setStripePaymentStatus(StripePaymentStatus.SUCCEEDED);

        PaymentTransactionDTO updatedPaymentTransaction = paymentTransactionService.updatePaymentTransaction(lockedPaymentTransaction);

        // Since transaction success, we should also update the payment
        PaymentDTO paymentDTO = paymentService.lockPaymentById(updatedPaymentTransaction.getPaymentId());

        paymentDTO.setTotalPaidAmount(paymentDTO.getTotalPaidAmount().add(updatedPaymentTransaction.getAmount()));

        paymentDTO.setPaymentStatus(PaymentStatus.COMPLETED);

        PaymentDTO updatedPayment = paymentService.updatePayment(paymentDTO);

        // Since the payment is success, should send a message using kafka back to orderService to tell that the order is success
        return null;
    }
}
