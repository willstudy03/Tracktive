package com.tracktive.productservice.repository.Impl;

import com.tracktive.productservice.model.DTO.RetailerInventoryDTO;
import com.tracktive.productservice.model.Enum.ProductStatus;
import com.tracktive.productservice.model.entity.RetailerInventory;
import com.tracktive.productservice.repository.RetailerInventoryRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class RetailerInventoryRepositoryImplTest {

    @Autowired
    private RetailerInventoryRepository retailerInventoryRepository;
    private static final Logger logger = LoggerFactory.getLogger(RetailerInventoryRepositoryImplTest.class);

    @Test
    void selectAllRetailerInventory() {
        List<RetailerInventoryDTO> retailerInventories = retailerInventoryRepository.selectAllRetailerInventory();
        logger.info("RetailerInventory:{}", retailerInventories);
        assertEquals(retailerInventories.isEmpty(),false);
    }

    @Test
    void selectRetailerInventoryByRetailerId() {
        String retailerId = "retailer123";
        List<RetailerInventoryDTO> retailerInventories = retailerInventoryRepository.selectRetailerInventoryByRetailerId(retailerId);
        logger.info("RetailerInventory:{}", retailerInventories);
        assertEquals(retailerInventories.isEmpty(),false);
    }

    @Test
    void selectRetailerInventoryById() {
        String id = "6b09ced1-1f98-40c9-9d89-072abf273ac0";
        Optional<RetailerInventoryDTO> retailerInventories = retailerInventoryRepository.selectRetailerInventoryById(id);
        logger.info("RetailerInventory:{}", retailerInventories);
        assertEquals(retailerInventories.get().getRetailerInventoryId(),id);
    }

    @Test
    void lockRetailerInventoryById() {
        String id = "6b09ced1-1f98-40c9-9d89-072abf273ac0";
        Optional<RetailerInventoryDTO> retailerInventories = retailerInventoryRepository.lockRetailerInventoryById(id);
        logger.info("RetailerInventory:{}", retailerInventories);
        assertEquals(retailerInventories.get().getRetailerInventoryId(),id);
    }

    @Test
    void addRetailerInventory() {
        RetailerInventoryDTO retailerInventoryDTO = new RetailerInventoryDTO();
        retailerInventoryDTO.setRetailerInventoryId("6b09ced1-1f98-40c9-9d89-072abf273ac0");
        retailerInventoryDTO.setRetailerId("retailer123"); // Sample retailer ID
        retailerInventoryDTO.setProductId("product456");   // Sample product ID
        retailerInventoryDTO.setStockQuantity(100);        // Initial stock
        retailerInventoryDTO.setReorderLevel(10);          // Reorder threshold
        retailerInventoryDTO.setProductStatus(ProductStatus.ACTIVE);   // Product status

        boolean result = retailerInventoryRepository.addRetailerInventory(retailerInventoryDTO);
        assertTrue(result);
    }

    @Test
    void updateRetailerInventory() {
        String id = "6b09ced1-1f98-40c9-9d89-072abf273ac0";
        Optional<RetailerInventoryDTO> retailerInventories = retailerInventoryRepository.lockRetailerInventoryById(id);
        RetailerInventoryDTO updateInventory = retailerInventories.get();
        updateInventory.setReorderLevel(5);
        boolean isUpdated = retailerInventoryRepository.updateRetailerInventory(updateInventory);
        assertTrue(isUpdated);
    }

    @Test
    void deleteRetailerInventoryById() {
        String id = "6b09ced1-1f98-40c9-9d89-072abf273ac0";
        boolean isDeleted = retailerInventoryRepository.deleteRetailerInventoryById(id);
        assertTrue(isDeleted);
    }
}