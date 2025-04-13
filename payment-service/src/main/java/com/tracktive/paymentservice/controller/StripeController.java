package com.tracktive.paymentservice.controller;

import com.stripe.model.checkout.Session;
import com.tracktive.paymentservice.model.DTO.PaymentDTO;
import com.tracktive.paymentservice.service.PaymentService;
import com.tracktive.paymentservice.stripe.StripeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
* Description: Stripe API end point testing
* @author William Theo
* @date 13/4/2025
*/
@RestController
@RequestMapping("api/payments")
public class StripeController {

    private final StripeService stripeService;

    private final PaymentService paymentService;

    @Autowired
    public StripeController(StripeService stripeService, PaymentService paymentService) {
        this.stripeService = stripeService;
        this.paymentService = paymentService;
    }

    @PostMapping("{PaymentId}")
    public ResponseEntity<Map<String, String>> createPaymentIntent(@PathVariable String PaymentId) {

        PaymentDTO paymentDTO = paymentService.lockPaymentById(PaymentId);
        Session session = stripeService.createCheckoutSession(paymentDTO);
        Map<String, String> response = new HashMap<>();
        response.put("checkoutUrl", session.getUrl());
        response.put("sessionId", session.getId());
        return ResponseEntity.ok(response);
    }
}
