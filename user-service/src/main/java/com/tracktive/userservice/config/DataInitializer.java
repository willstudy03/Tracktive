package com.tracktive.userservice.config;

import com.tracktive.userservice.model.DTO.UserCreationRequestDTO;
import com.tracktive.userservice.model.DTO.UserDTO;
import com.tracktive.userservice.model.Enum.UserRole;
import com.tracktive.userservice.service.UserManagementService;
import com.tracktive.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DataInitializer {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final UserService userService;
    private final UserManagementService userManagementService;
    private final Environment environment;

    @Autowired
    public DataInitializer(UserService userService, UserManagementService userManagementService, Environment environment) {
        this.userService = userService;
        this.userManagementService = userManagementService;
        this.environment = environment;
    }

    // Call this method manually when you want to initialize data
    @Transactional
    public void initializeData() {
        if (!Arrays.asList(environment.getActiveProfiles()).contains("prod")) {
            log.info("Skipping data initialization - not running in production profile");
            return;
        }

        log.info("Starting data initialization in production profile...");
        createDefaultAdminAccount();
        log.info("Data initialization completed.");
    }

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
            adminRequest.setPhoneNumber("0123456789");
            adminRequest.setRetailerDetailsDTO(null);
            adminRequest.setSupplierDetailsDTO(null);

            UserDTO createdAdmin = userManagementService.createUser(adminRequest);

            log.info("Default admin account created successfully with ID: {} and email: {}",
                    createdAdmin.getId(), createdAdmin.getEmail());

        } catch (Exception e) {
            log.error("Failed to create default admin account: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to initialize default admin account", e);
        }
    }

    private boolean adminAccountExists(String email) {
        try {
            return userService.selectAllUsers().stream()
                    .anyMatch(user -> email.equals(user.getEmail()) &&
                            user.getUserRole() == UserRole.ADMIN);

        } catch (Exception e) {
            log.warn("Error checking if admin account exists: {}", e.getMessage());
            return false;
        }
    }
}