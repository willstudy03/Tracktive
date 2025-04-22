package com.tracktive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
* Description: API Gateway Entry Point
* @author William Theo
* @date 22/4/2025
*/
@SpringBootApplication
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class);
        System.out.println( "API Gateway Running" );
    }
}
