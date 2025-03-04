package com.tracktive.userservice.repository.Impl;

import com.tracktive.userservice.model.DTO.SupplierDTO;
import com.tracktive.userservice.model.entity.Supplier;
import com.tracktive.userservice.repository.SupplierRepository;
import com.tracktive.userservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

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
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setSupplierId("7394fj29-1674-4448-89e9-1fe00286ab4d");
        supplierDTO.setSsmRegistrationNumber("38247923843");
        supplierDTO.setBusinessName("Best Tire");
        supplierDTO.setBusinessAddress("Ayer Keroh");
        supplierDTO.setBankAccount("23874923479234");
        supplierDTO.setBankName("CIMB");

        boolean result = supplierRepository.addSupplier(supplierDTO);
        assertTrue(result);
    }

    @Test
    void selectAllSuppliers() {
        List<SupplierDTO> supplierDTOS = supplierRepository.selectAllSuppliers();
        assertEquals(supplierDTOS.isEmpty(),false);
    }

    @Test
    void selectSupplierById() {
        String id = "7394fj29-1674-4448-89e9-1fe00286ab4d";
        Optional<SupplierDTO> supplierDTO = supplierRepository.selectSupplierById(id);
        SupplierDTO result = supplierDTO.get();
        assertEquals(result.getSupplierId(), id);
    }

    @Test
    void lockSupplierById() {
        String id = "7394fj29-1674-4448-89e9-1fe00286ab4d";
        Optional<SupplierDTO> supplierDTO = supplierRepository.lockSupplierById(id);
        logger.info("Acquired lock for user: " + supplierDTO.get().getBusinessName());
    }

    @Test
    void updateSupplier() {
        String id = "7394fj29-1674-4448-89e9-1fe00286ab4d";
        Optional<SupplierDTO> supplierDTO = supplierRepository.selectSupplierById(id);
        SupplierDTO updateSupplierDTO = supplierDTO.get();
        logger.info("Business Name:{}", updateSupplierDTO.getBusinessName());
        updateSupplierDTO.setBusinessName("Peformance Tiree");
        assertTrue(supplierRepository.updateSupplier(updateSupplierDTO));
    }

    @Test
    void deleteById() {
        String id = "7394fj29-1674-4448-89e9-1fe00286ab4d";
        boolean deleteResult = supplierRepository.deleteById(id);
        assertTrue(deleteResult);
    }
}