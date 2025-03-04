package com.tracktive.userservice.repository.Impl;

import com.tracktive.userservice.model.DTO.RetailerDTO;
import com.tracktive.userservice.model.entity.Retailer;
import com.tracktive.userservice.repository.RetailerRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
* Description: retailerRepository CRUD Test Case
* @author William Theo
* @date 2/3/2025
*/
@SpringBootTest
class RetailerRepositoryImplTest {

    @Autowired
    private RetailerRepository retailerRepository;
    private static final Logger logger = LoggerFactory.getLogger(RetailerRepositoryImplTest.class);

    @Test
    void addRetailer() {
        RetailerDTO retailerDTO = new RetailerDTO();
        retailerDTO.setRetailerId("23468c87-1674-4448-89e9-1fe00286ab4d");
        retailerDTO.setSsmRegistrationNumber("234234234");
        retailerDTO.setBusinessName("Performance Tire");
        retailerDTO.setBusinessAddress("TAMAN MERDEKA");
        retailerDTO.setBankAccount("2334234234234");
        retailerDTO.setBankName("MAYBANK");
        retailerDTO.setPayByTermCredit(3);

        boolean result = retailerRepository.addRetailer(retailerDTO);
        assertTrue(result);
    }

    @Test
    void selectAllRetailers() {
        List<RetailerDTO> retailerDTOs = retailerRepository.selectAllRetailers();
        assertEquals(retailerDTOs.isEmpty(),false);
    }

    @Test
    void selectRetailerById() {
        String id = "23468c87-1674-4448-89e9-1fe00286ab4d";
        Optional<RetailerDTO> retailerDTO = retailerRepository.selectRetailerById(id);
        RetailerDTO result = retailerDTO.get();
        assertEquals(result.getRetailerId(), id);
    }

    @Test
    void lockRetailerById() {
        String id = "23468c87-1674-4448-89e9-1fe00286ab4d";
        Optional<RetailerDTO> retailerDTO = retailerRepository.lockRetailerById(id);
        logger.info("Acquired lock for user: " + retailerDTO);
    }



    @Test
    void updateRetailer() {
        Optional<RetailerDTO> retailerDTO = retailerRepository.selectRetailerById("23468c87-1674-4448-89e9-1fe00286ab4d");
        RetailerDTO updateRetailerDTO = retailerDTO.get();
        logger.info("Business Name:{}", updateRetailerDTO.getBusinessName());
        updateRetailerDTO.setBusinessName("Peformance Tiree");
        assertTrue(retailerRepository.updateRetailer(updateRetailerDTO));
    }

    @Test
    void deleteById() {
        String id = "23468c87-1674-4448-89e9-1fe00286ab4d";
        boolean deleteResult = retailerRepository.deleteById(id);
        Optional<RetailerDTO> retailerDTO = retailerRepository.selectRetailerById(id);
        assertEquals(retailerDTO, Optional.empty());
        assertTrue(deleteResult);
    }
}