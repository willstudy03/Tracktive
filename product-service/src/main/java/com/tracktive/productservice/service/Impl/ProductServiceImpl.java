package com.tracktive.productservice.service.Impl;

import com.tracktive.productservice.exception.LockAcquisitionException;
import com.tracktive.productservice.exception.ProductNotFoundException;
import com.tracktive.productservice.model.DTO.ProductDTO;
import com.tracktive.productservice.model.DTO.ProductRequestDTO;
import com.tracktive.productservice.repository.ProductRepository;
import com.tracktive.productservice.service.ProductService;
import com.tracktive.productservice.util.converter.Impl.ProductConverter;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
* Description: Product Service Implementation
* @author William Theo
* @date 11/3/2025
*/
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final Validator validator;
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, Validator validator) {
        this.productRepository = productRepository;
        this.validator = validator;
    }

    @Override
    public List<ProductDTO> selectAllProducts() {
        return productRepository.selectAllProducts();
    }

    @Override
    public ProductDTO selectProductById(String id) {
        validateId(id);
        return productRepository.selectProductById(id)
                .orElseThrow(() -> {
                    logger.warn("Product not found with id: {}", id);
                    return new ProductNotFoundException("Product not found with id: " + id);
                });
    }

    @Override
    @Transactional
    public ProductDTO lockProductById(String id) {
        validateId(id);
        try {
            return productRepository.lockProductById(id)
                    .orElseThrow(() -> {
                        logger.warn("Failed to lock product, product not found with id: {}", id);
                        return new ProductNotFoundException("Product not found with id: " + id);
                    });
        } catch (PersistenceException e) {
            logger.error("Persistence error occurred during lock acquisition for product id: {}", id, e);
            throw new LockAcquisitionException("Failed to acquire lock for product with id: " + id, e);
        } catch (Exception e) {
            logger.error("Unexpected error during product lock for id: {}", id, e);
            throw new RuntimeException("Unexpected error during lock operation", e);
        }
    }

    @Override
    @Transactional
    public ProductDTO addProduct(ProductRequestDTO productRequestDTO) {

        validateProductRequest(productRequestDTO);

        ProductDTO productDTO = ProductConverter.toDTO(productRequestDTO);

        boolean result = productRepository.addProduct(productDTO);
        if (!result) {
            logger.error("Failed to add product with id: {}", productDTO.getProductId());
            throw new RuntimeException("Failed to add product with id: " + productDTO.getProductId());
        }
        logger.info("Product [{}] added successfully", productDTO.getProductId());

        // Fetch the inserted product details (if needed)
        return productRepository.selectProductById(productDTO.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Failed to fetch product after insertion"));
    }

    @Override
    @Transactional
    public ProductDTO updateProduct(ProductDTO productDTO) {

        validateProductDTO(productDTO);

        boolean result = productRepository.updateProduct(productDTO);

        if (!result) {
            logger.warn("Product not found for update with id: {}", productDTO.getProductId());
            throw new ProductNotFoundException("Product not found with id: " + productDTO.getProductId());
        }
        logger.info("Product [{}] updated successfully", productDTO.getProductId());

        return productRepository.selectProductById(productDTO.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Failed to fetch product after update"));
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        validateId(id);
        boolean result = productRepository.deleteById(id);
        if (!result) {
            logger.warn("Product not found for deletion with id: {}", id);
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        logger.info("Product [{}] deleted successfully", id);
    }

    private void validateId(String id) {
        if (Objects.isNull(id) || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be null or empty");
        }
    }

    private void validateProductRequest(ProductRequestDTO productRequestDTO) {
        Set<ConstraintViolation<ProductRequestDTO>> violations = validator.validate(productRequestDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation failed for ProductRequestDTO", violations);
        }
    }

    private void validateProductDTO(ProductDTO productDTO) {
        Set<ConstraintViolation<ProductDTO>> violations = validator.validate(productDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation failed for ProductDTO", violations);
        }
    }
}
