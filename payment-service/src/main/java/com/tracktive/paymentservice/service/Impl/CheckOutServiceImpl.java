package com.tracktive.paymentservice.service.Impl;

import com.stripe.model.checkout.Session;
import com.tracktive.paymentservice.exception.InvalidPaymentStatusException;
import com.tracktive.paymentservice.exception.PaymentTransactionNotFoundException;
import com.tracktive.paymentservice.model.DTO.*;
import com.tracktive.paymentservice.model.Enum.PaymentStatus;
import com.tracktive.paymentservice.service.CheckOutService;
import com.tracktive.paymentservice.service.PaymentService;
import com.tracktive.paymentservice.service.PaymentTransactionService;
import com.tracktive.paymentservice.stripe.StripeService;
import com.tracktive.paymentservice.util.converter.PaymentTransactionConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* Description: Payment Processor Service Implementation
* @author William Theo
* @date 14/4/2025
*/
@Service
public class CheckOutServiceImpl implements CheckOutService {

    private final StripeService stripeService;

    private final PaymentService paymentService;

    private final PaymentTransactionService paymentTransactionService;

    private static final Logger log = LoggerFactory.getLogger(CheckOutServiceImpl.class);

    @Autowired
    public CheckOutServiceImpl(StripeService stripeService, PaymentService paymentService, PaymentTransactionService paymentTransactionService) {
        this.stripeService = stripeService;
        this.paymentService = paymentService;
        this.paymentTransactionService = paymentTransactionService;
    }

    @Override
    @Transactional
    public CheckOutResponseDTO initiatePayment(CheckOutRequestDTO checkOutRequestDTO) {

        // Retrieve payment
        PaymentDTO paymentDTO = paymentService.lockPaymentById(checkOutRequestDTO.getPaymentId());

        // Status validation, only payment with payment status PENDING can initiate a payment
        if (!paymentDTO.getPaymentStatus().equals(PaymentStatus.PENDING)) {
            throw new InvalidPaymentStatusException("Payment ID " + paymentDTO.getId() +
                    " is not in a payable state. Current status: " + paymentDTO.getPaymentStatus());
        }

        try {
            // Try to retrieve existing transaction
            PaymentTransactionDTO existingTransaction = paymentTransactionService.selectPaymentTransactionByPaymentId(checkOutRequestDTO.getPaymentId());

            // If found, return the existing session URL
            log.info("Existing payment transaction found for payment ID: {}", paymentDTO.getId());
            return new CheckOutResponseDTO(existingTransaction.getStripeSessionId(), existingTransaction.getSessionUrl());

        } catch (PaymentTransactionNotFoundException e) {
            // No existing transaction found, create a new one
            log.info("No existing payment transaction found for payment ID: {}. Creating new session.", paymentDTO.getId());

            // Initiate a payment session with Stripe
            Session session = stripeService.createCheckoutSession(paymentDTO);

            // Create a paymentTransaction to track the Stripe session
            PaymentTransactionDTO paymentTransactionDTO = paymentTransactionService
                    .addPaymentTransaction(PaymentTransactionConverter.toRequest(paymentDTO, session));


            log.info("Payment status updated to PROCESSING for payment ID: {}", paymentDTO.getId());

            return new CheckOutResponseDTO(session.getId(), session.getUrl());
        }
    }
}
