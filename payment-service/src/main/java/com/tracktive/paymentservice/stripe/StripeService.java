package com.tracktive.paymentservice.stripe;

import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.param.checkout.SessionCreateParams;
import com.tracktive.paymentservice.model.DTO.PaymentDTO;
import com.tracktive.paymentservice.model.DTO.PaymentProcessRequestDTO;
import com.tracktive.paymentservice.service.PaymentProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final PaymentProcessService paymentProcessService;

    private static final Logger logger = LoggerFactory.getLogger(StripeService.class);

    @Autowired
    public StripeService(PaymentProcessService paymentProcessService) {
        this.paymentProcessService = paymentProcessService;
    }

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
                    Session session = (Session) event.getDataObjectDeserializer().getObject().get();
                    paymentProcessService.processPaymentSuccess(new PaymentProcessRequestDTO(session));
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

}
