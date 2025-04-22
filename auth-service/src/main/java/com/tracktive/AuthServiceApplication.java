package com.tracktive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
* Description: Authentication Service Entry Point
* @author William Theo
* @date 22/4/2025
*/
@SpringBootApplication
public class AuthServiceApplication {
    public static void main( String[] args ){
        SpringApplication.run(AuthServiceApplication.class);
        System.out.println( "Auth Service Running" );
    }
}
