package com.tracktive.productservice.repository.Impl;

import com.tracktive.productservice.model.DTO.ProductDTO;
import com.tracktive.productservice.model.Enum.ProductCategory;
import com.tracktive.productservice.model.Enum.ProductStatus;
import com.tracktive.productservice.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
* Description: Product Repository CRUD Test Case
* @author William Theo
* @date 7/3/2025
*/
@SpringBootTest
class ProductRepositoryImplTest {

    @Autowired
    private ProductRepository productRepository;
    private static final Logger logger = LoggerFactory.getLogger(ProductRepositoryImplTest.class);

    @Test
    void selectAllProducts() {
        List<ProductDTO> productDTOS = productRepository.selectAllProducts();
        logger.info("Products:{}", productDTOS);
        assertEquals(productDTOS.isEmpty(),false);
    }

    @Test
    void selectProductById() {
        String id = "01edbd0f-863f-4e65-b81d-025cdd430aa3";
        Optional<ProductDTO> productDTO = productRepository.selectProductById(id);
        ProductDTO resultDTO = productDTO.get();
        assertEquals(id, resultDTO.getProductId());
    }

    @Test
    void lockProductById() {
        String id = "01edbd0f-863f-4e65-b81d-025cdd430aa3";
        Optional<ProductDTO> productDTO = productRepository.lockProductById(id);
        ProductDTO resultDTO = productDTO.get();
        assertEquals(id, resultDTO.getProductId());
        logger.info("Acquired lock for product: " + resultDTO.getProductName());
    }

    @Test
    void addProduct() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId("01edbd0f-863f-4e65-b81d-025cdd430aa3");
        productDTO.setProductCategory(ProductCategory.TIRE);
        productDTO.setProductBrand("Michelin");
        productDTO.setProductName("Michelin Pilot Sport 4");
        productDTO.setProductDescription("High-performance summer tire for passenger cars.");
        productDTO.setRecommendedPrice(BigDecimal.valueOf(450.00));
        productDTO.setImageUrl("ashdbjahsgdjhasdimage-urlasjdkasdhj");
        productDTO.setProductStatus(ProductStatus.ACTIVE);

        boolean isAdded = productRepository.addProduct(productDTO);
        assertTrue(isAdded);
    }

    @Test
    void updateProduct() {
        String id = "01edbd0f-863f-4e65-b81d-025cdd430aa3";
        Optional<ProductDTO> productDTO = productRepository.selectProductById(id);
        ProductDTO resultDTO = productDTO.get();
        resultDTO.setProductName("Michelin Pilot Sport 5");

        boolean isUpdated = productRepository.updateProduct(resultDTO);
        assertTrue(isUpdated);
    }

    @Test
    void deleteById() {
        String id = "01edbd0f-863f-4e65-b81d-025cdd430aa3";
        boolean delResult = productRepository.deleteById(id);
        assertTrue(delResult);
    }
}