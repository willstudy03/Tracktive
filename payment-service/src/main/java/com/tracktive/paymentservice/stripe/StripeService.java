package com.tracktive.paymentservice.stripe;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
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

}
