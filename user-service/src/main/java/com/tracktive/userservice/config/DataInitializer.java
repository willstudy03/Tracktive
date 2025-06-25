package com.tracktive.userservice.config;

import com.tracktive.userservice.model.DTO.UserCreationRequestDTO;
import com.tracktive.userservice.model.DTO.UserDTO;
import com.tracktive.userservice.model.Enum.UserRole;
import com.tracktive.userservice.service.UserManagementService;
import com.tracktive.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * Description: Data Initializer for creating default admin account
 * @author William Theo
 * @date 25/6/2025
 */
@Component
@Profile({"production", "prod"})  // Support both 'production' and 'prod' profiles
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final UserService userService;
    private final UserManagementService userManagementService;
    private final Environment environment;  // Add Environment for programmatic check

    @Autowired
    public DataInitializer(UserService userService, UserManagementService userManagementService, Environment environment) {
        this.userService = userService;
        this.userManagementService = userManagementService;
        this.environment = environment;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Optional: Additional programmatic check (redundant with @Profile but shown for completeness)
        if (!Arrays.asList(environment.getActiveProfiles()).contains("prod")) {
            log.info("Skipping data initialization - not running in production profile");
            return;
        }

        log.info("Starting data initialization in production profile...");
        createDefaultAdminAccount();
        log.info("Data initialization completed.");
    }

    // Alternative approach using @PostConstruct
    // @PostConstruct
    // @Transactional
    // public void init() {
    //     log.info("Starting data initialization...");
    //     createDefaultAdminAccount();
    //     log.info("Data initialization completed.");
    // }

    private void createDefaultAdminAccount() {
        String defaultAdminEmail = "tracktive@gmail.com";

        try {
            // Check if admin account already exists
            if (adminAccountExists(defaultAdminEmail)) {
                log.info("Default admin account already exists with email: {}", defaultAdminEmail);
                return;
            }

            // Create default admin account
            UserCreationRequestDTO adminRequest = new UserCreationRequestDTO();
            adminRequest.setEmail(defaultAdminEmail);
            adminRequest.setUserRole(UserRole.ADMIN);
            adminRequest.setName("Admin");
            adminRequest.setPhoneNumber("000-000-0000"); // Set appropriate default


            UserDTO createdAdmin = userManagementService.createUser(adminRequest);

            log.info("Default admin account created successfully with ID: {} and email: {}",
                    createdAdmin.getId(), createdAdmin.getEmail());

        } catch (Exception e) {
            log.error("Failed to create default admin account: {}", e.getMessage(), e);
            // You might want to decide whether to throw the exception or just log it
            // throw new RuntimeException("Failed to initialize default admin account", e);
        }
    }

    private boolean adminAccountExists(String email) {
        try {
            // You'll need to implement a method to check if user exists by email
            // This assumes you have such a method in your UserService
            // If not, you might need to add it or use a different approach

            // Option 1: If you have a method to find user by email
            // UserDTO existingUser = userService.selectUserByEmail(email);
            // return existingUser != null && existingUser.getUserRole() == UserRole.ADMIN;

            // Option 2: Get all users and check (less efficient for large datasets)
            return userService.selectAllUsers().stream()
                    .anyMatch(user -> email.equals(user.getEmail()) &&
                            user.getUserRole() == UserRole.ADMIN);

        } catch (Exception e) {
            log.warn("Error checking if admin account exists: {}", e.getMessage());
            return false;
        }
    }
}