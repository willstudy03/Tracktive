package com.tracktive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
* Description: Delivery Service Entry Point
* @author William Theo
* @date 21/3/2025
*/
@SpringBootApplication
public class DeliveryServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DeliveryServiceApplication.class, args);
        System.out.println("Delivery Service Running");
    }
}
