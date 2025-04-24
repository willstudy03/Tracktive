package com.tracktive.userservice.service.Impl;

import com.tracktive.userservice.kafka.UserEventProducer;
import com.tracktive.userservice.model.DTO.*;
import com.tracktive.userservice.model.Enum.UserRole;
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
import org.springframework.beans.BeanUtils;
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
    public UserManagementResponseDTO selectUserById(String id) {

        // Step 1: Query basic user
        UserDTO userDTO = userService.selectUserById(id);

        UserManagementResponseDTO userManagementResponseDTO = new UserManagementResponseDTO();
        BeanUtils.copyProperties(userDTO, userManagementResponseDTO);

        // Based on the user role to query role-related details
        switch (userDTO.getUserRole()) {
            case RETAILER:

                RetailerDTO retailerDTO = retailerService.selectRetailerById(userDTO.getId());

                RetailerDetailsDTO retailerDetailsDTO = new RetailerDetailsDTO();

                BeanUtils.copyProperties(retailerDTO, retailerDetailsDTO);

                userManagementResponseDTO.setRetailerDetailsDTO(retailerDetailsDTO);

                log.info("Retailer details retrieved successfully with supplier ID: {}", retailerDTO.getRetailerId());

                break;

            case SUPPLIER:

                SupplierDTO supplierDTO = supplierService.selectSupplierById(userDTO.getId());

                SupplierDetailsDTO supplierDetailsDTO = new SupplierDetailsDTO();

                BeanUtils.copyProperties(supplierDTO, supplierDetailsDTO);

                userManagementResponseDTO.setSupplierDetailsDTO(supplierDetailsDTO);

                log.info("Supplier details retrieved successfully with supplier ID: {}", supplierDTO.getSupplierId());

                break;

            case ADMIN:
                log.info("User {} is an ADMIN - no additional details needed to be retrieved", userDTO.getId());
                break;

            default:
                log.warn("Unhandled user role: {} for retrieve role info with user ID: {}", userDTO.getUserRole(), userDTO.getId());
        }
        log.info("Completed retrieving user details for ID: {}", id);
        return userManagementResponseDTO;
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

    @Override
    @Transactional
    public UserManagementResponseDTO updateUser(UserManagementRequestDTO userManagementRequestDTO) {
        String userId = userManagementRequestDTO.getId();
        log.info("Starting update for user ID: {}", userId);

        // Step 1: Lock basic user
        UserDTO userDTO = userService.selectUserById(userId);

        // Step 2: Update the basic user info
        BeanUtils.copyProperties(userManagementRequestDTO, userDTO);
        UserDTO updatedUserDTO = userService.updateUser(userDTO);

        // Step 3: Based on the user role to update role-related details
        switch (userDTO.getUserRole()) {
            case RETAILER:
                if (userManagementRequestDTO.getRetailerDetailsDTO() != null) {
                    RetailerDTO retailerDTO = retailerService.lockRetailerById(userId);
                    BeanUtils.copyProperties(userManagementRequestDTO.getRetailerDetailsDTO(), retailerDTO);
                    retailerService.updateRetailer(retailerDTO);
                    log.info("Retailer details updated successfully with retailer ID: {}", retailerDTO.getRetailerId());
                }
                break;

            case SUPPLIER:
                if (userManagementRequestDTO.getSupplierDetailsDTO() != null) {
                    SupplierDTO supplierDTO = supplierService.lockSupplierById(userId);
                    BeanUtils.copyProperties(userManagementRequestDTO.getSupplierDetailsDTO(), supplierDTO);
                    supplierService.updateSupplier(supplierDTO);
                    log.info("Supplier details updated successfully with supplier ID: {}", supplierDTO.getSupplierId());
                }
                break;

            case ADMIN:
                log.info("User {} is an ADMIN - no additional details needed to be updated", userId);
                break;

            default:
                log.warn("Unhandled user role: {} for update info with user ID: {}", userDTO.getUserRole(), userId);
        }

        // Step 4: Return the updated user data
        log.info("User update completed for ID: {}", userId);
        return selectUserById(userId);
    }

    @Override
    @Transactional
    public UserDTO deleteUserById(String id) {

        UserDTO userDTO = userService.lockUserById(id);

        if (userDTO.getUserRole().equals(UserRole.ADMIN)){
            // admin account is not able to deleted by admin
            throw new IllegalArgumentException("Admin accounts cannot be deleted");
        }

        // delete user from the db
        userService.deleteUserById(userDTO.getId());

        // sent user deleted message
        userEventProducer.sendUserDeletedEvent(id);

        log.info("User deletion completed for user ID: {}", id);

        return userDTO;
    }
}
