package com.tracktive.productservice.service.Impl;

import com.tracktive.productservice.exception.LockAcquisitionException;
import com.tracktive.productservice.exception.ProductNotFoundException;
import com.tracktive.productservice.model.DTO.SupplierProductDTO;
import com.tracktive.productservice.model.DTO.SupplierProductRequestDTO;
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

/**
* Description: Supplier Product Service Implementation
* @author William Theo
* @date 13/3/2025
*/
@Service
public class SupplierProductServiceImpl implements SupplierProductService {

    private final SupplierProductRepository supplierProductRepository;

    private final Validator validator;

    private static final Logger logger = LoggerFactory.getLogger(SupplierProductServiceImpl.class);

    @Autowired
    public SupplierProductServiceImpl(SupplierProductRepository supplierProductRepository, Validator validator) {
        this.supplierProductRepository = supplierProductRepository;
        this.validator = validator;
    }

    @Override
    public List<SupplierProductDTO> selectAllSupplierProducts() {
        return supplierProductRepository.selectAllSupplierProducts();
    }

    @Override
    public List<SupplierProductDTO> selectSupplierProductsBySupplierId(String supplierId) {
        validateSupplierId(supplierId);
        return supplierProductRepository.selectSupplierProductsBySupplierId(supplierId);
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
    public void deleteSupplierProductById(String id) {
        validateId(id);
        boolean result = supplierProductRepository.deleteSupplierProductById(id);
        if (!result) {
            logger.error("Failed to delete supplier product with id: {}", id);
            throw new ProductNotFoundException("Failed to delete supplier product with id: " + id);
        }
        logger.info("Supplier Product [{}] deleted successfully", id);
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
