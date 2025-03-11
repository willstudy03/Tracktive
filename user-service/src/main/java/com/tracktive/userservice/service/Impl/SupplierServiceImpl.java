package com.tracktive.userservice.service.Impl;

import com.tracktive.userservice.exception.LockAcquisitionException;
import com.tracktive.userservice.exception.UserNotFoundException;
import com.tracktive.userservice.model.DTO.SupplierDTO;
import com.tracktive.userservice.model.DTO.UserDTO;
import com.tracktive.userservice.repository.SupplierRepository;
import com.tracktive.userservice.service.SupplierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * Description: Supplier CRUD Service Implementation
 * @author William Theo
 * @date 5/3/2025
 */
@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    private static final Logger logger = LoggerFactory.getLogger(SupplierServiceImpl.class);

    @Override
    public List<SupplierDTO> selectAllSuppliers() {
        return supplierRepository.selectAllSuppliers();
    }

    @Override
    public SupplierDTO selectSupplierById(String id) {
        validateId(id);
        return supplierRepository.selectSupplierById(id)
                .orElseThrow(()->{
                    logger.warn("Supplier not found with id: {}", id);
                    return new UserNotFoundException("Supplier not found with id: " + id);
                });
    }

    @Override
    @Transactional
    public SupplierDTO lockSupplierById(String id) {
        validateId(id);
        try {
            return supplierRepository.lockSupplierById(id)
                    .orElseThrow(() -> {
                        logger.warn("Failed to lock supplier, supplier not found with id: {}", id);
                        return new UserNotFoundException("Supplier not found with id: " + id);
                    });
        } catch (CannotAcquireLockException e) {
            logger.error("Lock acquisition failed for supplier id: {}", id, e);
            throw new LockAcquisitionException("Failed to acquire lock for supplier with id: " + id, e);
        } catch (Exception e) {
            logger.error("Unexpected error during supplier lock for id: {}", id, e);
            throw new RuntimeException("Unexpected error during lock operation", e);
        }
    }

    @Override
    @Transactional
    public void addSupplier(SupplierDTO supplierDTO) {
        validateSupplierDTO(supplierDTO);
        boolean result = supplierRepository.addSupplier(supplierDTO);
        if (!result) {
            logger.error("Failed to add supplier with id: {}", supplierDTO.getSupplierId());
            throw new RuntimeException("Failed to add supplier with id: " + supplierDTO.getSupplierId());
        }
        logger.info("Supplier [{}] added successfully", supplierDTO.getSupplierId());
    }

    @Override
    @Transactional
    public void updateSupplier(SupplierDTO supplierDTO) {
        validateSupplierDTO(supplierDTO);
        boolean result = supplierRepository.updateSupplier(supplierDTO);
        if (!result) {
            logger.error("Failed to update supplier with id: {}", supplierDTO.getSupplierId());
            throw new UserNotFoundException("Failed to update supplier with id: " + supplierDTO.getSupplierId());
        }
        logger.info("Supplier [{}] updated successfully", supplierDTO.getSupplierId());
    }

    @Override
    @Transactional
    public void deleteSupplierById(String id) {
        validateId(id);
        boolean deleted = supplierRepository.deleteById(id);
        if (!deleted) {
            logger.info("Failed to delete: Supplier not found with id: {}", id);
            throw new UserNotFoundException("Failed to delete: Supplier not found with id: " + id);
        }
        logger.info("Supplier [{}] deleted successfully", id);
    }

    private void validateId(String id){
        if (Objects.isNull(id) || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
    }

    private void validateSupplierDTO(SupplierDTO supplierDTO) {
        if (Objects.isNull(supplierDTO)) {
            throw new IllegalArgumentException("SupplierDTO cannot be null");
        }
        //@TODO: Validation
    }
}
