package com.tracktive.productservice.service.Impl;

import com.tracktive.productservice.exception.LockAcquisitionException;
import com.tracktive.productservice.exception.ProductNotFoundException;
import com.tracktive.productservice.model.DTO.RetailerInventoryDTO;
import com.tracktive.productservice.model.DTO.RetailerInventoryRequestDTO;
import com.tracktive.productservice.repository.RetailerInventoryRepository;
import com.tracktive.productservice.service.RetailerInventoryService;
import com.tracktive.productservice.util.converter.Impl.RetailerInventoryConverter;
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

@Service
public class RetailerInventoryServiceImpl implements RetailerInventoryService {

    private final RetailerInventoryRepository retailerInventoryRepository;

    private final Validator validator;

    private static final Logger logger = LoggerFactory.getLogger(RetailerInventoryServiceImpl.class);

    @Autowired
    public RetailerInventoryServiceImpl(RetailerInventoryRepository retailerInventoryRepository, Validator validator) {
        this.retailerInventoryRepository = retailerInventoryRepository;
        this.validator = validator;
    }

    @Override
    public List<RetailerInventoryDTO> selectAllRetailerInventory() {
        return retailerInventoryRepository.selectAllRetailerInventory();
    }

    @Override
    public List<RetailerInventoryDTO> selectRetailerInventoryByRetailerId(String retailerId) {
        validateRetailerId(retailerId);
        return retailerInventoryRepository.selectRetailerInventoryByRetailerId(retailerId);
    }

    @Override
    public RetailerInventoryDTO selectRetailerInventoryById(String id) {
        validateId(id);
        return retailerInventoryRepository.selectRetailerInventoryById(id)
                .orElseThrow(() -> {
                    logger.warn("Retailer Inventory not found with id: {}", id);
                    return new ProductNotFoundException("Retailer Inventory not found with id: " + id);
                });
    }

    @Override
    @Transactional
    public RetailerInventoryDTO lockRetailerInventoryById(String id) {
        validateId(id);
        try {
            return retailerInventoryRepository.lockRetailerInventoryById(id)
                    .orElseThrow(() -> {
                        logger.warn("Failed to lock retailer inventory, retailer inventory not found with id: {}", id);
                        return new ProductNotFoundException("Retailer Inventory not found with id: " + id);
                    });
        } catch (PersistenceException e) {
            logger.error("Persistence error occurred during lock acquisition for retailer inventory id: {}", id, e);
            throw new LockAcquisitionException("Failed to acquire lock for retailer inventory with id: " + id, e);
        } catch (Exception e) {
            logger.error("Unexpected error during retailer inventory lock for id: {}", id, e);
            throw new RuntimeException("Unexpected error during lock operation", e);
        }
    }

    @Override
    @Transactional
    public RetailerInventoryDTO addRetailerInventory(RetailerInventoryRequestDTO retailerInventoryRequestDTO) {

        validateRetailerInventoryRequestDTO(retailerInventoryRequestDTO);

        RetailerInventoryDTO retailerInventoryDTO = RetailerInventoryConverter.toDTO(retailerInventoryRequestDTO);

        boolean result = retailerInventoryRepository.addRetailerInventory(retailerInventoryDTO);

        if (!result) {
            logger.error("Failed to add retailer inventory with id: {}", retailerInventoryDTO.getRetailerInventoryId());
            throw new RuntimeException("Failed to add retailer inventory with id: " + retailerInventoryDTO.getRetailerInventoryId());
        }
        logger.info("Retailer Inventory [{}] added successfully", retailerInventoryDTO.getRetailerInventoryId());

        return retailerInventoryRepository.selectRetailerInventoryById(retailerInventoryDTO.getRetailerInventoryId())
                .orElseThrow(() -> new ProductNotFoundException("Failed to fetch retailer inventory after insertion"));
    }

    @Override
    @Transactional
    public RetailerInventoryDTO updateRetailerInventory(RetailerInventoryDTO retailerInventoryDTO) {

        validateRetailerInventoryDTO(retailerInventoryDTO);

        boolean result = retailerInventoryRepository.updateRetailerInventory(retailerInventoryDTO);

        if (!result) {
            logger.error("Failed to update retailer inventory with id: {}", retailerInventoryDTO.getRetailerInventoryId());
            throw new ProductNotFoundException("Failed to update retailer inventory with id: " + retailerInventoryDTO.getRetailerInventoryId());
        }
        logger.info("Retailer Inventory [{}] updated successfully", retailerInventoryDTO.getRetailerInventoryId());

        return retailerInventoryRepository.selectRetailerInventoryById(retailerInventoryDTO.getRetailerInventoryId())
                .orElseThrow(() -> new ProductNotFoundException("Failed to fetch retailer inventory after insertion"));
    }

    @Override
    @Transactional
    public void deleteRetailerInventoryById(String id) {
        validateId(id);
        boolean result = retailerInventoryRepository.deleteRetailerInventoryById(id);
        if (!result) {
            logger.error("Failed to delete retailer inventory with id: {}", id);
            throw new ProductNotFoundException("Failed to delete retailer inventory with id: " + id);
        }
        logger.info("Retailer Inventory [{}] deleted successfully", id);
    }

    private void validateId(String id){
        if (Objects.isNull(id) || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Retailer Inventory ID cannot be null or empty");
        }
    }

    private void validateRetailerId(String retailerId){
        if (Objects.isNull(retailerId) || retailerId.trim().isEmpty()) {
            throw new IllegalArgumentException("Retailer Id cannot be null or empty");
        }
    }

    private void validateRetailerInventoryDTO(RetailerInventoryDTO retailerInventoryDTO) {
        Set<ConstraintViolation<RetailerInventoryDTO>> violations = validator.validate(retailerInventoryDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation failed for Retailer Inventory DTO", violations);
        }
    }

    private void validateRetailerInventoryRequestDTO(RetailerInventoryRequestDTO retailerInventoryRequestDTO) {
        Set<ConstraintViolation<RetailerInventoryRequestDTO>> violations = validator.validate(retailerInventoryRequestDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation failed for Retailer Inventory Request DTO", violations);
        }
    }
}
