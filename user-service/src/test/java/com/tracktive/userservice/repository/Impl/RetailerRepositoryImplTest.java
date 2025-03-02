package com.tracktive.userservice.repository.Impl;

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
    private static final Logger logger = LoggerFactory.getLogger(RetailerRepositoryImpl.class);

    @Test
    void addRetailer() {
        Retailer retailer = new Retailer();
        retailer.setRetailerId("23468c87-1674-4448-89e9-1fe00286ab4d");
        retailer.setSsmRegistrationNumber("234234234");
        retailer.setBusinessName("Performance Tire");
        retailer.setBusinessAddress("TAMAN MERDEKA");
        retailer.setBankAccount("2334234234234");
        retailer.setBankName("MAYBANK");
        retailer.setPayByTermCredit(3);

        boolean result = retailerRepository.addRetailer(retailer);
        assertTrue(result);
    }

    @Test
    void selectAllRetailers() {
        List<Retailer> retailers = retailerRepository.selectAllRetailers();
        assertEquals(retailers.isEmpty(),false);
    }

    @Test
    void selectRetailerById() {
        String id = "23468c87-1674-4448-89e9-1fe00286ab4d";
        Optional<Retailer> retailer = retailerRepository.selectRetailerById(id);
        Retailer result = retailer.get();
        assertEquals(result.getRetailerId(), id);
    }

    @Test
    void lockRetailerById() {
        String id = "23468c87-1674-4448-89e9-1fe00286ab4d";
        Optional<Retailer> retailer = retailerRepository.lockRetailerById(id);
        logger.info("Acquired lock for user: " + retailer);
    }



    @Test
    void updateRetailer() {
        Optional<Retailer> retailer = retailerRepository.selectRetailerById("23468c87-1674-4448-89e9-1fe00286ab4d");
        Retailer updateRetailer = retailer.get();
        logger.info("Business Name:{}", updateRetailer.getBusinessName());
        updateRetailer.setBusinessName("Peformance Tiree");
        assertTrue(retailerRepository.updateRetailer(updateRetailer));
    }

    @Test
    void deleteById() {
        String id = "23468c87-1674-4448-89e9-1fe00286ab4d";
        boolean deleteResult = retailerRepository.deleteById(id);
        Optional<Retailer> retailer = retailerRepository.selectRetailerById(id);
        assertEquals(retailer, Optional.empty());
        assertTrue(deleteResult);
    }
}