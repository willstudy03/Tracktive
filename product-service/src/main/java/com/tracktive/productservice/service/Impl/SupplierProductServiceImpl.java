package com.tracktive.productservice.service.Impl;

import com.tracktive.productservice.exception.LockAcquisitionException;
import com.tracktive.productservice.exception.ProductNotFoundException;
import com.tracktive.productservice.model.DTO.SupplierProductDTO;
import com.tracktive.productservice.repository.SupplierProductRepository;
import com.tracktive.productservice.service.SupplierProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
* Description: Supplier Product Service Implementation
* @author William Theo
* @date 13/3/2025
*/
@Service
public class SupplierProductServiceImpl implements SupplierProductService {

    private final SupplierProductRepository supplierProductRepository;

    private static final Logger logger = LoggerFactory.getLogger(SupplierProductServiceImpl.class);

    @Autowired
    public SupplierProductServiceImpl(SupplierProductRepository supplierProductRepository) {
        this.supplierProductRepository = supplierProductRepository;
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
        } catch (CannotAcquireLockException e) {
            logger.error("Lock acquisition failed for supplier product id: {}", id, e);
            throw new LockAcquisitionException("Failed to acquire lock for supplier product with id: " + id, e);
        } catch (Exception e) {
            logger.error("Unexpected error during supplier product lock for id: {}", id, e);
            throw new RuntimeException("Unexpected error during lock operation", e);
        }
    }

    @Override
    @Transactional
    public void addSupplierProduct(SupplierProductDTO supplierProductDTO) {
        validateSupplierProductDTO(supplierProductDTO);
        boolean result = supplierProductRepository.addSupplierProduct(supplierProductDTO);
        if (!result) {
            logger.error("Failed to add supplier product with id: {}", supplierProductDTO.getSupplierProductId());
            throw new RuntimeException("Failed to add supplier product with id: " + supplierProductDTO.getSupplierProductId());
        }
        logger.info("Supplier Product [{}] added successfully", supplierProductDTO.getSupplierProductId());
    }

    @Override
    @Transactional
    public void updateSupplierProduct(SupplierProductDTO supplierProductDTO) {
        validateSupplierProductDTO(supplierProductDTO);
        boolean result = supplierProductRepository.updateSupplierProduct(supplierProductDTO);
        if (!result) {
            logger.error("Failed to update supplier product with id: {}", supplierProductDTO.getSupplierProductId());
            throw new RuntimeException("Failed to update supplier product with id: " + supplierProductDTO.getSupplierProductId());
        }
        logger.info("Supplier Product [{}] updated successfully", supplierProductDTO.getSupplierProductId());
    }

    @Override
    @Transactional
    public void deleteSupplierProductById(String id) {
        validateId(id);
        boolean result = supplierProductRepository.deleteSupplierProductById(id);
        if (!result) {
            logger.error("Failed to delete supplier product with id: {}", id);
            throw new RuntimeException("Failed to delete supplier product with id: " + id);
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
        if (Objects.isNull(supplierProductDTO)) {
            throw new IllegalArgumentException("SupplierProductDTO cannot be null");
        }
        //TODO: Validation
    }
}
