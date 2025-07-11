package com.tracktive.productservice.service.Impl;

import com.tracktive.productservice.exception.InsufficientStockException;
import com.tracktive.productservice.exception.LockAcquisitionException;
import com.tracktive.productservice.exception.ProductNotFoundException;
import com.tracktive.productservice.exception.StockDeductionException;
import com.tracktive.productservice.model.DTO.ProductDTO;
import com.tracktive.productservice.model.DTO.SupplierProductDTO;
import com.tracktive.productservice.model.DTO.SupplierProductRequestDTO;
import com.tracktive.productservice.model.VO.SupplierProductVO;
import com.tracktive.productservice.repository.ProductRepository;
import com.tracktive.productservice.repository.SupplierProductRepository;
import com.tracktive.productservice.service.SupplierProductService;
import com.tracktive.productservice.util.converter.Impl.SupplierProductConverter;
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
import java.util.stream.Collectors;

/**
* Description: Supplier Product Service Implementation
* @author William Theo
* @date 13/3/2025
*/
@Service
public class SupplierProductServiceImpl implements SupplierProductService {

    private final SupplierProductRepository supplierProductRepository;

    private final ProductRepository productRepository;

    private final Validator validator;

    private static final Logger logger = LoggerFactory.getLogger(SupplierProductServiceImpl.class);

    @Autowired
    public SupplierProductServiceImpl(SupplierProductRepository supplierProductRepository, ProductRepository productRepository, Validator validator) {
        this.supplierProductRepository = supplierProductRepository;
        this.productRepository = productRepository;
        this.validator = validator;
    }

    @Override
    public List<SupplierProductVO> selectAllSupplierProducts() {
        return supplierProductRepository.selectAllSupplierProducts()
                .stream()
                .map(supplierProductDTO -> {
                    ProductDTO product = productRepository
                            .selectProductById(supplierProductDTO.getProductId())
                            .orElse(null); // Allow null if product not found
                    return SupplierProductConverter.toVO(supplierProductDTO, product);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<SupplierProductVO> selectSupplierProductsBySupplierId(String supplierId) {
        validateSupplierId(supplierId);
        return supplierProductRepository.selectSupplierProductsBySupplierId(supplierId)
                .stream()
                .map(supplierProductDTO -> {
                    ProductDTO product = productRepository
                            .selectProductById(supplierProductDTO.getProductId())
                            .orElse(null); // Allow null if product not found
                    return SupplierProductConverter.toVO(supplierProductDTO, product);
                })
                .collect(Collectors.toList());
    }

    @Override
    public SupplierProductDTO selectSupplierProductById(String id) {
        validateId(id);
        return supplierProductRepository.selectSupplierProductById(id)
                .orElseThrow(() -> {
                    logger.warn("Supplier product not found with id: {}", id);
                    return new ProductNotFoundException("Supplier product not found with id: " + id);
                });
    }

    @Override
    @Transactional
    public SupplierProductDTO lockSupplierProductById(String id) {
        validateId(id);
        try {
            return supplierProductRepository.lockSupplierProductById(id)
                    .orElseThrow(() -> {
                        logger.warn("Failed to lock supplier product, supplier not found with id: {}", id);
                        return new ProductNotFoundException("Supplier product not found with id: " + id);
                    });
        } catch (PersistenceException e) {
            logger.error("Persistence error occurred during lock acquisition for supplier product id: {}", id, e);
            throw new LockAcquisitionException("Failed to acquire lock for supplier product with id: " + id, e);
        } catch (Exception e) {
            logger.error("Unexpected error during supplier product lock for id: {}", id, e);
            throw new RuntimeException("Unexpected error during lock operation", e);
        }
    }

    @Override
    @Transactional
    public SupplierProductDTO addSupplierProduct(SupplierProductRequestDTO supplierProductRequestDTO) {

        validateSupplierProductRequestDTO(supplierProductRequestDTO);

        SupplierProductDTO supplierProductDTO = SupplierProductConverter.toDTO(supplierProductRequestDTO);

        boolean result = supplierProductRepository.addSupplierProduct(supplierProductDTO);
        if (!result) {
            logger.error("Failed to add supplier product with id: {}", supplierProductDTO.getSupplierProductId());
            throw new RuntimeException("Failed to add supplier product with id: " + supplierProductDTO.getSupplierProductId());
        }
        logger.info("Supplier Product [{}] added successfully", supplierProductDTO.getSupplierProductId());

        return supplierProductRepository.selectSupplierProductById(supplierProductDTO.getSupplierProductId())
                .orElseThrow(() -> new ProductNotFoundException("Failed to fetch supplier product after insertion"));
    }

    @Override
    @Transactional
    public SupplierProductDTO updateSupplierProduct(SupplierProductDTO supplierProductDTO) {
        validateSupplierProductDTO(supplierProductDTO);
        boolean result = supplierProductRepository.updateSupplierProduct(supplierProductDTO);
        if (!result) {
            logger.error("Failed to update supplier product with id: {}", supplierProductDTO.getSupplierProductId());
            throw new RuntimeException("Failed to update supplier product with id: " + supplierProductDTO.getSupplierProductId());
        }
        logger.info("Supplier Product [{}] updated successfully", supplierProductDTO.getSupplierProductId());

        return supplierProductRepository.selectSupplierProductById(supplierProductDTO.getSupplierProductId())
                .orElseThrow(() -> new ProductNotFoundException("Failed to fetch supplier product after update"));
    }

    @Override
    @Transactional
    public SupplierProductDTO deductSupplierProductStock(String supplierProductId, int quantity){

        validateId(supplierProductId);

        validateStockQuantity(quantity);

        // First get the current product to check availability
        SupplierProductDTO product = supplierProductRepository.selectSupplierProductById(supplierProductId)
                .orElseThrow(() -> {
                    logger.warn("Supplier product not found with id: {}", supplierProductId);
                    return new ProductNotFoundException("Supplier product not found with id: " + supplierProductId);
                });

        // Verify sufficient stock (double-check even though we validated earlier)
        if (product.getStockQuantity() < quantity) {
            logger.error("Insufficient stock for product {}: requested {}, available {}",
                    supplierProductId, quantity, product.getStockQuantity());
            throw new InsufficientStockException("Insufficient stock for product: " + supplierProductId);
        }

        // Update the stock quantity
        product.setStockQuantity(product.getStockQuantity() - quantity);

        // Save the updated product
        boolean result = supplierProductRepository.updateSupplierProduct(product);
        if (!result) {
            logger.error("Failed to deduct stock for product with id: {}", supplierProductId);
            throw new StockDeductionException("Failed to deduct stock for product with id: " + supplierProductId);
        }

        logger.info("Successfully deducted {} units from product {}", quantity, supplierProductId);

        // Return the updated product
        return supplierProductRepository.selectSupplierProductById(supplierProductId)
                .orElseThrow(() -> new ProductNotFoundException("Failed to fetch supplier product after stock deduction"));

    }


    @Override
    @Transactional
    public void deleteSupplierProductById(String id) {
        validateId(id);
        boolean result = supplierProductRepository.deleteSupplierProductById(id);
        if (!result) {
            logger.error("Failed to delete supplier product with id: {}", id);
            throw new ProductNotFoundException("Failed to delete supplier product with id: " + id);
        }
        logger.info("Supplier Product [{}] deleted successfully", id);
    }

    private void validateStockQuantity(int quantity){
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity to deduct must be greater than zero");
        }
    }

    private void validateId(String id){
        if (Objects.isNull(id) || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Supplier Product ID cannot be null or empty");
        }
    }

    private void validateSupplierId(String supplierId){
        if (Objects.isNull(supplierId) || supplierId.trim().isEmpty()) {
            throw new IllegalArgumentException("Supplier Id cannot be null or empty");
        }
    }

    private void validateSupplierProductDTO(SupplierProductDTO supplierProductDTO) {
        Set<ConstraintViolation<SupplierProductDTO>> violations = validator.validate(supplierProductDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation failed for Supplier Product DTO", violations);
        }
    }

    private void validateSupplierProductRequestDTO(SupplierProductRequestDTO supplierProductRequestDTO) {
        Set<ConstraintViolation<SupplierProductRequestDTO>> violations = validator.validate(supplierProductRequestDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation failed for Supplier Product Request DTO", violations);
        }
    }

}
