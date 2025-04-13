package com.tracktive.paymentservice.stripe;

import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
* Description:
* @author William Theo
* @date 12/4/2025
*/
@Configuration
public class StripConfig {

    @Value("${stripe.api.secret.key}")
    private String stripeSecretKey;

    @Value("${stripe.webhook.secret}")
    private String webhookSecret;

    @PostConstruct
    public void init(){
        Stripe.apiKey = stripeSecretKey;
    }

    @Bean
    public String stripeSecretKey() {
        return stripeSecretKey;
    }

    @Bean
    public String webhookSecret() {
        return webhookSecret;
    }
}
