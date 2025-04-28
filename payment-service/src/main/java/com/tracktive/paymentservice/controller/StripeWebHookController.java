package com.tracktive.paymentservice.controller;

import com.stripe.model.Event;
import com.tracktive.paymentservice.stripe.StripeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stripe")
public class StripeWebHookController {

    private final StripeService stripeService;

    @Autowired
    public StripeWebHookController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> handleStripeWebhook(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) {
        try {
            Event event = stripeService.handleWebhookEvent(payload, sigHeader);
            return ResponseEntity.ok("Webhook processed successfully: " + event.getType());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Webhook error: " + e.getMessage());
        }
    }

}
