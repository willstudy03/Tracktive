package com.tracktive.userservice.service.Impl;

import com.tracktive.userservice.model.DTO.UserCreationRequestDTO;
import com.tracktive.userservice.model.DTO.UserDTO;
import com.tracktive.userservice.service.RetailerService;
import com.tracktive.userservice.service.SupplierService;
import com.tracktive.userservice.service.UserManagementService;
import com.tracktive.userservice.service.UserService;
import jakarta.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final Validator validator;

    private static final Logger log = LoggerFactory.getLogger(UserManagementServiceImpl.class);

    @Autowired
    public UserManagementServiceImpl(UserService userService, SupplierService supplierService, RetailerService retailerService, Validator validator) {
        this.userService = userService;
        this.supplierService = supplierService;
        this.retailerService = retailerService;
        this.validator = validator;
    }

    @Override
    @Transactional
    public UserDTO crateUser(UserCreationRequestDTO userCreationRequestDTO) {




        return null;
    }
}
