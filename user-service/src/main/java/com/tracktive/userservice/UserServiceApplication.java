package com.tracktive.userservice;

import com.tracktive.userservice.config.DataInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
* Description: User Service Entry Point
* @author William Theo
* @date 27/2/2025
*/
@SpringBootApplication
public class UserServiceApplication {
    public static void main(String[] args) {
        var context = SpringApplication.run(UserServiceApplication.class, args);
        // Retrieve the DataInitializer bean from the context
        DataInitializer initializer = context.getBean(DataInitializer.class);
        // Manually call the initializer
        initializer.initializeData();
        System.out.println("User Service Running");
    }
}