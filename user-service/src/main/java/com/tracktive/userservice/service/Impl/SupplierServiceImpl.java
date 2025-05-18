package com.tracktive.userservice.service.Impl;

import com.tracktive.userservice.exception.DuplicateEmailException;
import com.tracktive.userservice.exception.DuplicateSSMException;
import com.tracktive.userservice.exception.LockAcquisitionException;
import com.tracktive.userservice.exception.UserNotFoundException;
import com.tracktive.userservice.model.DTO.SupplierDTO;
import com.tracktive.userservice.model.DTO.UserDTO;
import com.tracktive.userservice.model.DTO.UserRequestDTO;
import com.tracktive.userservice.repository.SupplierRepository;
import com.tracktive.userservice.service.SupplierService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Description: Supplier CRUD Service Implementation
 * @author William Theo
 * @date 5/3/2025
 */
@Service
public class SupplierServiceImpl implements SupplierService {

    private final Validator validator;

    private final SupplierRepository supplierRepository;

    private static final Logger logger = LoggerFactory.getLogger(SupplierServiceImpl.class);

    @Autowired
    public SupplierServiceImpl(Validator validator, SupplierRepository supplierRepository) {
        this.validator = validator;
        this.supplierRepository = supplierRepository;
    }

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
        } catch (PersistenceException e) {
            logger.error("Persistence error occurred during lock acquisition for supplier id: {}", id, e);
            throw new LockAcquisitionException("Failed to acquire lock for supplier with id: " + id, e);
        } catch (Exception e) {
            logger.error("Unexpected error during supplier lock for id: {}", id, e);
            throw new RuntimeException("Unexpected error during lock operation", e);
        }
    }

    @Override
    @Transactional
    public SupplierDTO addSupplier(SupplierDTO supplierDTO) {
        validateSupplierDTO(supplierDTO);

        // Ensure no same ssm
        boolean ssmExists = supplierRepository.selectAllSuppliers()
                .stream()
                .anyMatch(supplier -> supplier.getSsmRegistrationNumber().equals(supplierDTO.getSsmRegistrationNumber()));

        if (ssmExists) {
            logger.error("User with ssm {} already exists", supplierDTO.getSsmRegistrationNumber());
            throw new DuplicateSSMException("User with SSM " + supplierDTO.getSsmRegistrationNumber() + " already exists");
        }

        boolean result = supplierRepository.addSupplier(supplierDTO);
        if (!result) {
            logger.error("Failed to add supplier with id: {}", supplierDTO.getSupplierId());
            throw new RuntimeException("Failed to add supplier with id: " + supplierDTO.getSupplierId());
        }
        logger.info("Supplier [{}] added successfully", supplierDTO.getSupplierId());

        return supplierRepository.selectSupplierById(supplierDTO.getSupplierId())
                .orElseThrow(()-> new UserNotFoundException("Failed to fetch supplier after insertion"));
    }

    @Override
    @Transactional
    public SupplierDTO updateSupplier(SupplierDTO supplierDTO) {
        validateSupplierDTO(supplierDTO);
        boolean result = supplierRepository.updateSupplier(supplierDTO);
        if (!result) {
            logger.error("Failed to update supplier with id: {}", supplierDTO.getSupplierId());
            throw new UserNotFoundException("Failed to update supplier with id: " + supplierDTO.getSupplierId());
        }
        logger.info("Supplier [{}] updated successfully", supplierDTO.getSupplierId());

        return supplierRepository.selectSupplierById(supplierDTO.getSupplierId())
                .orElseThrow(()-> new UserNotFoundException("Failed to fetch supplier after update"));
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
        Set<ConstraintViolation<SupplierDTO>> violations = validator.validate(supplierDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation failed for supplierDTO", violations);
        }
    }
}
