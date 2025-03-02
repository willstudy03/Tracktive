package com.tracktive.userservice.repository.Impl;

import com.tracktive.userservice.model.DAO.SupplierDAO;
import com.tracktive.userservice.model.Enum.UserRole;
import com.tracktive.userservice.model.entity.Retailer;
import com.tracktive.userservice.model.entity.Supplier;
import com.tracktive.userservice.model.entity.User;
import com.tracktive.userservice.repository.SupplierRepository;
import com.tracktive.userservice.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
* Description: Supplier Repository CRUD Test Case
* @author William Theo
* @date 2/3/2025
* @param
* @return
*/
@SpringBootTest
class SupplierRepositoryImplTest {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(SupplierRepositoryImplTest.class);

    @Test
    void addSupplier() {
        Supplier supplier = new Supplier();
        supplier.setSupplierId("7394fj29-1674-4448-89e9-1fe00286ab4d");
        supplier.setSsmRegistrationNumber("38247923843");
        supplier.setBusinessName("Best Tire");
        supplier.setBusinessAddress("Ayer Keroh");
        supplier.setBankAccount("23874923479234");
        supplier.setBankName("CIMB");

        boolean result = supplierRepository.addSupplier(supplier);
        assertTrue(result);
    }

    @Test
    void selectAllSuppliers() {
        List<Supplier> suppliers = supplierRepository.selectAllSuppliers();
        assertEquals(suppliers.isEmpty(),false);
    }

    @Test
    void selectSupplierById() {
        String id = "7394fj29-1674-4448-89e9-1fe00286ab4d";
        Optional<Supplier> supplier = supplierRepository.selectSupplierById(id);
        Supplier result = supplier.get();
        assertEquals(result.getSupplierId(), id);
    }

    @Test
    void lockUserById() {
        String id = "7394fj29-1674-4448-89e9-1fe00286ab4d";
        Optional<Supplier> supplier = supplierRepository.lockUserById(id);
        logger.info("Acquired lock for user: " + supplier.get().getBusinessName());
    }

    @Test
    void updateSupplier() {
        String id = "7394fj29-1674-4448-89e9-1fe00286ab4d";
        Optional<Supplier> supplier = supplierRepository.selectSupplierById(id);
        Supplier updateSupplier = supplier.get();
        logger.info("Business Name:{}", updateSupplier.getBusinessName());
        updateSupplier.setBusinessName("Peformance Tiree");
        assertTrue(supplierRepository.updateSupplier(updateSupplier));
    }

    @Test
    void deleteById() {
        String id = "7394fj29-1674-4448-89e9-1fe00286ab4d";
        boolean deleteResult = supplierRepository.deleteById(id);
        assertTrue(deleteResult);
    }
}