package com.tracktive.userservice.service.Impl;

import com.tracktive.userservice.kafka.UserEventProducer;
import com.tracktive.userservice.model.DTO.RetailerDTO;
import com.tracktive.userservice.model.DTO.SupplierDTO;
import com.tracktive.userservice.model.DTO.UserCreationRequestDTO;
import com.tracktive.userservice.model.DTO.UserDTO;
import com.tracktive.userservice.service.RetailerService;
import com.tracktive.userservice.service.SupplierService;
import com.tracktive.userservice.service.UserManagementService;
import com.tracktive.userservice.service.UserService;
import com.tracktive.userservice.util.converter.RetailerConverter;
import com.tracktive.userservice.util.converter.SupplierConverter;
import com.tracktive.userservice.util.converter.UserConverter;
import jakarta.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* Description: User Management Service Implementation
* @author William Theo
* @date 22/4/2025
*/
@Service
public class UserManagementServiceImpl implements UserManagementService {

    private final UserService userService;

    private final SupplierService supplierService;

    private final RetailerService retailerService;

    private final UserEventProducer userEventProducer;

    private final Validator validator;

    private static final Logger log = LoggerFactory.getLogger(UserManagementServiceImpl.class);

    @Autowired
    public UserManagementServiceImpl(UserService userService, SupplierService supplierService, RetailerService retailerService, UserEventProducer userEventProducer, Validator validator) {
        this.userService = userService;
        this.supplierService = supplierService;
        this.retailerService = retailerService;
        this.userEventProducer = userEventProducer;
        this.validator = validator;
    }

    @Override
    public List<UserDTO> selectAllUsers() {
        return userService.selectAllUsers();
    }

    @Override
    @Transactional
    public UserDTO createUser(UserCreationRequestDTO userCreationRequestDTO) {

        // Step 1: Create basic user
        UserDTO userDTO = userService.addUser(UserConverter.toUserRequestDTO(userCreationRequestDTO));

        log.info("Created basic user with ID: {} and role: {}", userDTO.getId(), userDTO.getUserRole());

        // Send 2: Publish Event to authentication service to create a default user credential using email
        userEventProducer.sendUserCreatedEvent(userDTO.getId(), userDTO.getEmail());

        // Step 3: Save the role specific details into the db
        switch (userDTO.getUserRole()){
            case RETAILER:
                RetailerDTO retailerDTO = retailerService.addRetailer(RetailerConverter.toDTO(userDTO, userCreationRequestDTO.getRetailerDetailsDTO()));
                log.info("Retailer details created successfully with retailer ID: {}", retailerDTO.getRetailerId());
                break;
            case SUPPLIER:
                SupplierDTO supplierDTO = supplierService.addSupplier(SupplierConverter.toDTO(userDTO, userCreationRequestDTO.getSupplierDetailsDTO()));
                log.info("Supplier details created successfully with supplier ID: {}", supplierDTO.getSupplierId());
                break;
            case ADMIN:
                log.info("User {} is an ADMIN - no additional details needed", userDTO.getId());
                break;
            default:
                log.warn("Unhandled user role: {} for user ID: {}", userDTO.getUserRole(), userDTO.getId());
        }

        log.info("User creation completed for user ID: {}", userDTO.getId());

        return userDTO;
    }


}
