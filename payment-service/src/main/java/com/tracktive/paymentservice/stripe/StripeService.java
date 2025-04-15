package com.tracktive.paymentservice.stripe;

import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.param.checkout.SessionCreateParams;
import com.tracktive.paymentservice.model.DTO.PaymentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
* Description: Stripe Service
* @author William Theo
* @date 12/4/2025
*/
@Service
public class StripeService {

    @Value("${stripe.success.url}")
    private String successUrl;

    @Value("${stripe.cancel.url}")
    private String cancelUrl;

    @Value("${stripe.webhook.secret}")
    private String webhookSecret;

    private static final Logger logger = LoggerFactory.getLogger(StripeService.class);


    public Session createCheckoutSession(PaymentDTO paymentDTO) {
        try {
            // Convert payment amount to cents (Stripe uses smallest currency unit)
            long amountInCents = paymentDTO.getAmount().multiply(new BigDecimal("100")).longValue();

            SessionCreateParams params = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl(successUrl + "?session_id={CHECKOUT_SESSION_ID}")
                    .setCancelUrl(cancelUrl)
                    .setClientReferenceId(paymentDTO.getId()) // Store payment ID for reference
                    .addLineItem(
                            SessionCreateParams.LineItem.builder()
                                    .setPriceData(
                                            SessionCreateParams.LineItem.PriceData.builder()
                                                    .setCurrency(paymentDTO.getCurrency().toLowerCase())
                                                    .setUnitAmount(amountInCents)
                                                    .setProductData(
                                                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                    .setName("Order #" + paymentDTO.getOrderId())
                                                                    .setDescription("Payment for order #" + paymentDTO.getOrderId())
                                                                    .build()
                                                    )
                                                    .build()
                                    )
                                    .setQuantity(1L)
                                    .build()
                    )
                    .build();

            Session session = Session.create(params);
            logger.info("Created Stripe Checkout Session: {}", session.getId());
            return session;
        } catch (StripeException e) {
            logger.error("Error creating Stripe Checkout Session", e);
            throw new RuntimeException("Failed to create Stripe checkout session", e);
        }
    }

    public Event handleWebhookEvent(String payload, String sigHeader) {
        try {
            Event event = Webhook.constructEvent(payload, sigHeader, webhookSecret);

            switch(event.getType()) {
                case "checkout.session.completed":
                    handlePaymentSuccess(event);
                    break;
                case "checkout.session.expired":
                    handlePaymentAbandoned(event);
                    break;
                case "payment_intent.payment_failed":
                    handlePaymentFailed(event);
                    break;
            }

            return event;
        } catch (SignatureVerificationException e) {
            logger.error("Invalid signature in Stripe webhook", e);
            throw new RuntimeException("Invalid Stripe webhook signature", e);
        } catch (Exception e) {
            logger.error("Error processing Stripe webhook", e);
            throw new RuntimeException("Error processing Stripe webhook", e);
        }
    }

    private void handlePaymentSuccess(Event event) {

        Session session = (Session) event.getDataObjectDeserializer().getObject().get();
        String clientReferenceId = session.getClientReferenceId(); // This is Payment ID

        logger.info("Payment succeeded for payment ID: {}", clientReferenceId);

        // Update your payment record in the database to COMPLETED/PAID status
        // paymentRepository.updatePaymentStatus(clientReferenceId, PaymentStatus.COMPLETED);

        // You can also store additional payment details from session if needed
        // String paymentIntentId = session.getPaymentIntent();
    }

    private void handlePaymentAbandoned(Event event) {
        Session session = (Session) event.getDataObjectDeserializer().getObject().get();
        String clientReferenceId = session.getClientReferenceId();

        logger.info("Payment abandoned for payment ID: {}", clientReferenceId);

        // Update payment status to ABANDONED or EXPIRED
        // paymentRepository.updatePaymentStatus(clientReferenceId, PaymentStatus.ABANDONED);
    }

    private void handlePaymentFailed(Event event) {
        PaymentIntent paymentIntent = (PaymentIntent) event.getDataObjectDeserializer().getObject().get();

        // Here you'd need to find your payment record associated with this PaymentIntent
        // This might require storing the payment intent ID when you receive the checkout.session.completed event
        // Or querying your database using metadata or other identifiers

        logger.info("Payment failed for payment intent: {}", paymentIntent.getId());
        String failureMessage = paymentIntent.getLastPaymentError() != null ?
                paymentIntent.getLastPaymentError().getMessage() :
                "Unknown error";

        // Update payment status to FAILED
        // paymentRepository.updatePaymentStatus(paymentId, PaymentStatus.FAILED, failureMessage);
    }

}
