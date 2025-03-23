package com.tracktive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
* Description: Payment Service Entry Point
* @author William Theo
* @date 23/3/2025
*/
@SpringBootApplication
public class PaymentServiceApplication {
    public static void main( String[] args ) {
        SpringApplication.run(PaymentServiceApplication.class);
        System.out.println("Payment Service Running");
    }
}
