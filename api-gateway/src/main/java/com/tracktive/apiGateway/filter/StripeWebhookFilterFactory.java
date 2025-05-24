package com.tracktive.apiGateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

/**
 * A specialized filter for Stripe webhook endpoints that skips authentication.
 * This provides a direct pass-through for webhook calls from Stripe to the payment service.
 */
@Component
public class StripeWebhookFilterFactory extends AbstractGatewayFilterFactory<StripeWebhookFilterFactory.Config> {

    private static final Logger logger = LoggerFactory.getLogger(StripeWebhookFilterFactory.class);

    public StripeWebhookFilterFactory() {
        super(Config.class);
    }

    @Override
    public String name() {
        return "StripeWebhook";
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String path = exchange.getRequest().getURI().getPath();
            logger.info("Stripe webhook filter processing path: {}", path);

            // Just pass through the request without authentication
            return chain.filter(exchange);
        };
    }

    public static class Config {
        // No configuration needed for this filter
    }
}
