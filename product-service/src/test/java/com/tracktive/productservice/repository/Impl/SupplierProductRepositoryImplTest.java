package com.tracktive.productservice.repository.Impl;

import com.tracktive.productservice.model.DTO.SupplierProductDTO;
import com.tracktive.productservice.model.DTO.TireDTO;
import com.tracktive.productservice.model.Enum.ProductStatus;
import com.tracktive.productservice.model.entity.SupplierProduct;
import com.tracktive.productservice.repository.SupplierProductRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
* Description: Supplier Product Repository CRUD Test Case
* @author William Theo
* @date 10/3/2025
*/
@SpringBootTest
class SupplierProductRepositoryImplTest {

    @Autowired
    private SupplierProductRepository supplierProductRepository;

    private static final Logger logger = LoggerFactory.getLogger(SupplierProductRepositoryImplTest.class);

    @Test
    void selectAllSupplierProducts() {
        List<SupplierProductDTO> supplierProductDTOS = supplierProductRepository.selectAllSupplierProducts();
        logger.info("SupplierProducts:{}", supplierProductDTOS);
        assertEquals(supplierProductDTOS.isEmpty(),false);
    }

    @Test
    void selectSupplierProductsBySupplierId() {
        String supplierId = "supplier123";
        List<SupplierProductDTO> supplierProductDTOS = supplierProductRepository.selectSupplierProductsBySupplierId(supplierId);
        logger.info("SupplierProducts:{}", supplierProductDTOS);
        assertEquals(supplierProductDTOS.isEmpty(),false);
    }

    @Test
    void selectSupplierProductById() {
        String id = "2acd2e6b-8495-4d2f-b1cf-b8a3f7d1df21";
        Optional<SupplierProductDTO> supplierProductDTO = supplierProductRepository.selectSupplierProductById(id);
        logger.info("SupplierProducts:{}", supplierProductDTO.get().getSupplierProductId());
        assertEquals(id, supplierProductDTO.get().getSupplierProductId());
    }

    @Test
    void lockSupplierProductById() {
        String id = "2acd2e6b-8495-4d2f-b1cf-b8a3f7d1df21";
        Optional<SupplierProductDTO> supplierProductDTO = supplierProductRepository.lockSupplierProductById(id);
        logger.info("SupplierProducts:{}", supplierProductDTO.get().getSupplierProductId());
        assertEquals(id, supplierProductDTO.get().getSupplierProductId());
    }

    @Test
    void addSupplierProduct() {
        SupplierProductDTO supplierProductDTO = new SupplierProductDTO();
        supplierProductDTO.setSupplierProductId("2acd2e6b-8495-4d2f-b1cf-b8a3f7d1df21");
        supplierProductDTO.setSupplierId("supplier123");
        supplierProductDTO.setProductId("product456");
        supplierProductDTO.setPrice(BigDecimal.valueOf(199.99));
        supplierProductDTO.setDiscountPercentage(BigDecimal.valueOf(10.00));
        supplierProductDTO.setStockQuantity(50);
        supplierProductDTO.setProductStatus(ProductStatus.ACTIVE);

        boolean isAdded = supplierProductRepository.addSupplierProduct(supplierProductDTO);
        assertTrue(isAdded);
    }

    @Test
    void updateSupplierProduct() {
        String id = "2acd2e6b-8495-4d2f-b1cf-b8a3f7d1df21";
        Optional<SupplierProductDTO> supplierProductDTO = supplierProductRepository.selectSupplierProductById(id);
        SupplierProductDTO updateProductDTO = supplierProductDTO.get();
        updateProductDTO.setProductStatus(ProductStatus.DISCONTINUED);
        boolean updated = supplierProductRepository.updateSupplierProduct(updateProductDTO);
        assertTrue(updated);
    }

    @Test
    void deleteSupplierProductById() {
        String id = "2acd2e6b-8495-4d2f-b1cf-b8a3f7d1df21";
        boolean isDeleted = supplierProductRepository.deleteSupplierProductById(id);
        assertTrue(isDeleted);
    }
}