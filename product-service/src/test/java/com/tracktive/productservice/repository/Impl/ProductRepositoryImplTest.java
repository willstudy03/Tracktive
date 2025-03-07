package com.tracktive.productservice.repository.Impl;

import com.tracktive.productservice.model.Enum.ProductCategory;
import com.tracktive.productservice.model.Enum.ProductStatus;
import com.tracktive.productservice.model.entity.Product;
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
        List<Product> products = productRepository.selectAllProducts();
        logger.info("Products:{}", products);
        assertEquals(products.isEmpty(),false);
    }

    @Test
    void selectProductById() {
        String id = "01edbd0f-863f-4e65-b81d-025cdd430aa3";
        Optional<Product> product = productRepository.selectProductById(id);
        Product result = product.get();
        assertEquals(id, result.getProductId());
    }

    @Test
    void lockProductById() {
        String id = "01edbd0f-863f-4e65-b81d-025cdd430aa3";
        Optional<Product> product = productRepository.lockProductById(id);
        Product result = product.get();
        assertEquals(id, result.getProductId());
        logger.info("Acquired lock for product: " + result.getProductName());
    }

    @Test
    void addProduct() {
        Product product = new Product();
        product.setProductId("01edbd0f-863f-4e65-b81d-025cdd430aa3");
        product.setProductCategory(ProductCategory.TIRE);
        product.setProductBrand("Michelin");
        product.setProductName("Michelin Pilot Sport 4");
        product.setProductDescription("High-performance summer tire for passenger cars.");
        product.setRecommendedPrice(BigDecimal.valueOf(450.00));
        product.setProductStatus(ProductStatus.ACTIVE);

        boolean isAdded = productRepository.addProduct(product);
        assertTrue(isAdded);
    }

    @Test
    void updateProduct() {
        String id = "01edbd0f-863f-4e65-b81d-025cdd430aa3";
        Optional<Product> product = productRepository.selectProductById(id);
        Product result = product.get();
        result.setProductName("Michelin Pilot Sport 5");

        boolean isUpdated = productRepository.updateProduct(result);
        assertTrue(isUpdated);
    }

    @Test
    void deleteById() {
        String id = "01edbd0f-863f-4e65-b81d-025cdd430aa3";
        boolean delResult = productRepository.deleteById(id);
        assertTrue(delResult);
    }
}