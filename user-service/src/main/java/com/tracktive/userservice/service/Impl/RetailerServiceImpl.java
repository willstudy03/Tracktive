package com.tracktive.userservice.service.Impl;

import com.tracktive.userservice.exception.LockAcquisitionException;
import com.tracktive.userservice.exception.UserNotFoundException;
import com.tracktive.userservice.model.DTO.RetailerDTO;
import com.tracktive.userservice.repository.RetailerRepository;
import com.tracktive.userservice.service.RetailerService;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * Description: Retailer CRUD Service Implementation
 * @author William Theo
 * @date 5/3/2025
 */
@Service
public class RetailerServiceImpl implements RetailerService {

    private final RetailerRepository retailerRepository;

    private static final Logger logger = LoggerFactory.getLogger(RetailerServiceImpl.class);

    @Autowired
    public RetailerServiceImpl(RetailerRepository retailerRepository) {
        this.retailerRepository = retailerRepository;
    }

    @Override
    public List<RetailerDTO> selectAllRetailers() {
        return retailerRepository.selectAllRetailers();
    }

    @Override
    public RetailerDTO selectRetailerById(String id) {
        validateId(id);
        return retailerRepository.selectRetailerById(id)
                .orElseThrow(()->{
                    logger.warn("Retailer not found with id: {}", id);
                    return new UserNotFoundException("Retailer not found with id: " + id);
                });
    }

    @Override
    @Transactional
    public RetailerDTO lockRetailerById(String id) {
        validateId(id);
        try {
            return retailerRepository.lockRetailerById(id)
                    .orElseThrow(() -> {
                        logger.warn("Failed to lock retailer, retailer not found with id: {}", id);
                        return new UserNotFoundException("Retailer not found with id: " + id);
                    });
        } catch (PersistenceException e) {
            logger.error("Persistence error occurred during lock acquisition for retailer id: {}", id, e);
            throw new LockAcquisitionException("Failed to acquire lock for retailer with id: " + id, e);
        } catch (Exception e) {
            logger.error("Unexpected error during retailer lock for id: {}", id, e);
            throw new RuntimeException("Unexpected error during lock operation", e);
        }
    }

    @Override
    @Transactional
    public void addRetailer(RetailerDTO retailerDTO) {
        validateRetailerDTO(retailerDTO);
        boolean result = retailerRepository.addRetailer(retailerDTO);
        if (!result) {
            logger.error("Failed to add retailer with id: {}", retailerDTO.getRetailerId());
            throw new RuntimeException("Failed to add retailer with id: " + retailerDTO.getRetailerId());
        }
        logger.info("Retailer [{}] added successfully", retailerDTO.getRetailerId());
    }

    @Override
    @Transactional
    public void updateRetailer(RetailerDTO retailerDTO) {
        validateRetailerDTO(retailerDTO);
        boolean result = retailerRepository.updateRetailer(retailerDTO);
        if (!result) {
            logger.error("Failed to update retailer with id: {}", retailerDTO.getRetailerId());
            throw new UserNotFoundException("Failed to update retailer with id: " + retailerDTO.getRetailerId());
        }
        logger.info("Retailer [{}] updated successfully", retailerDTO.getRetailerId());
    }

    @Override
    @Transactional
    public void deleteRetailerById(String id) {
        validateId(id);
        boolean result = retailerRepository.deleteById(id);
        if (!result) {
            logger.error("Failed to delete retailer with id: {}", id);
            throw new UserNotFoundException("Failed to delete retailer with id: " + id);
        }
        logger.info("Retailer [{}] deleted successfully", id);
    }

    private void validateId(String id){
        if (Objects.isNull(id) || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
    }

    private void validateRetailerDTO(RetailerDTO retailerDTO) {
        if (Objects.isNull(retailerDTO)) {
            throw new IllegalArgumentException("RetailerDTO cannot be null");
        }
        //@TODO: Validation
    }
}
