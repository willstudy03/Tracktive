package com.tracktive.productservice.service.Impl;

import com.tracktive.productservice.exception.LockAcquisitionException;
import com.tracktive.productservice.exception.ProductNotFoundException;
import com.tracktive.productservice.model.DTO.TireDTO;
import com.tracktive.productservice.repository.TireRepository;
import com.tracktive.productservice.service.TireService;
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
* Description: Tire Service Implementation
* @author William Theo
* @date 13/3/2025
*/
@Service
public class TireServiceImpl implements TireService {

    private final TireRepository tireRepository;
    private static final Logger logger = LoggerFactory.getLogger(TireServiceImpl.class);

    @Autowired
    public TireServiceImpl(TireRepository tireRepository) {
        this.tireRepository = tireRepository;
    }

    @Override
    public List<TireDTO> selectAllTire() {
        return tireRepository.selectAllTire();
    }

    @Override
    public List<TireDTO> selectTireByParams(TireDTO tireDTO) {
        validateTireDTO(tireDTO);
        return tireRepository.selectTireByParams(tireDTO);
    }

    @Override
    public TireDTO selectTireById(String id) {
        validateId(id);
        return tireRepository.selectTireById(id)
                .orElseThrow(() -> {
                    logger.warn("Tire not found with id: {}", id);
                    return new ProductNotFoundException("Tire not found with id: " + id);
                });
    }

    @Override
    @Transactional
    public TireDTO lockTireById(String id) {
        validateId(id);
        try {
            return tireRepository.lockTireById(id)
                    .orElseThrow(() -> {
                        logger.warn("Failed to lock tire, tire not found with id: {}", id);
                        return new ProductNotFoundException("Tire not found with id: " + id);
                    });
        } catch (PersistenceException e) {
            logger.error("Persistence error occurred during lock acquisition for tire id: {}", id, e);
            throw new LockAcquisitionException("Failed to acquire lock for tire with id: " + id, e);
        } catch (Exception e) {
            logger.error("Unexpected error during tire lock for id: {}", id, e);
            throw new RuntimeException("Unexpected error during lock operation", e);
        }
    }

    @Override
    public TireDTO selectTireBySKU(String sku) {
        validateSku(sku);
        return tireRepository.selectTireBySKU(sku)
                .orElseThrow(() -> {
                    logger.warn("Tire not found with sku: {}", sku);
                    return new ProductNotFoundException("Tire not found with sku: " + sku);
                });
    }

    @Override
    @Transactional
    public void addTire(TireDTO tireDTO) {

        validateTireDTO(tireDTO);
        boolean result = tireRepository.addTire(tireDTO);
        if (!result) {
            logger.error("Failed to add tire with id: {}", tireDTO.getId());
            throw new RuntimeException("Failed to add tire with id: " + tireDTO.getId());
        }
        logger.info("Tire [{}] added successfully", tireDTO.getId());
    }

    @Override
    @Transactional
    public void updateTire(TireDTO tireDTO) {
        validateTireDTO(tireDTO);
        boolean result = tireRepository.updateTire(tireDTO);
        if (!result) {
            logger.error("Failed to update tire with id: {}", tireDTO.getId());
            throw new RuntimeException("Failed to update tire with id: " + tireDTO.getId());
        }
        logger.info("Tire [{}] updated successfully", tireDTO.getId());
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        validateId(id);
        boolean result = tireRepository.deleteById(id);
        if (!result) {
            logger.error("Failed to delete tire with id: {}", id);
            throw new RuntimeException("Failed to delete tire with id: " + id);
        }
        logger.info("Tire [{}] deleted successfully", id);

    }

    private void validateId(String id){
        if (Objects.isNull(id) || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Tire ID cannot be null or empty");
        }
    }

    private void validateSku(String sku){
        if (Objects.isNull(sku) || sku.trim().isEmpty()) {
            throw new IllegalArgumentException("Tire SKU cannot be null or empty");
        }
    }

    private void validateTireDTO(TireDTO tireDTO) {
        if (Objects.isNull(tireDTO)) {
            throw new IllegalArgumentException("TireDTO cannot be null");
        }
        //@TODO: VALIDATION
    }
}
