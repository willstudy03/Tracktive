package com.tracktive.productservice.service.Impl;

import com.tracktive.productservice.exception.LockAcquisitionException;
import com.tracktive.productservice.exception.ProductNotFoundException;
import com.tracktive.productservice.exception.TireSKUAlreadyExistsException;
import com.tracktive.productservice.model.DTO.TireDTO;
import com.tracktive.productservice.model.DTO.TireRequestDTO;
import com.tracktive.productservice.repository.TireRepository;
import com.tracktive.productservice.service.TireService;
import com.tracktive.productservice.util.converter.Impl.TireConverter;
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
* Description: Tire Service Implementation
* @author William Theo
* @date 13/3/2025
*/
@Service
public class TireServiceImpl implements TireService {

    private final TireRepository tireRepository;

    private final Validator validator;

    private static final Logger logger = LoggerFactory.getLogger(TireServiceImpl.class);

    @Autowired
    public TireServiceImpl(TireRepository tireRepository, Validator validator) {
        this.tireRepository = tireRepository;
        this.validator = validator;
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
    public TireDTO addTire(TireRequestDTO tireRequestDTO) {

        validateTireRequestDTO(tireRequestDTO);

        TireDTO tireDTO = TireConverter.toDTO(tireRequestDTO);

        // Ensure no same ssm
        boolean skuExists = tireRepository.selectAllTire()
                .stream()
                .anyMatch(tire ->
                        tire.getTireSku().equals(tireDTO.getTireSku())
                );

        if (skuExists) {
            logger.error("Tire with SKU {} already exists", tireDTO.getTireSku());
            throw new TireSKUAlreadyExistsException("Tire with SKU " + tireDTO.getTireSku() + " already exists");
        }

        boolean result = tireRepository.addTire(tireDTO);
        if (!result) {
            logger.error("Failed to add tire with id: {}", tireDTO.getId());
            throw new RuntimeException("Failed to add tire with id: " + tireDTO.getId());
        }
        logger.info("Tire [{}] added successfully", tireDTO.getId());

        return tireRepository.selectTireById(tireDTO.getId())
                .orElseThrow(() -> new ProductNotFoundException("Failed to fetch tire after insertion"));
    }

    @Override
    @Transactional
    public TireDTO updateTire(TireDTO tireDTO) {

        validateTireDTO(tireDTO);

        // Ensure no same ssm
        boolean skuExists = tireRepository.selectAllTire()
                .stream()
                .anyMatch(tire ->
                        tire.getTireSku().equals(tireDTO.getTireSku()) &&
                                !tire.getTireSku().equals(tireDTO.getTireSku())
                );

        if (skuExists) {
            logger.error("Tire with SKU {} already exists", tireDTO.getTireSku());
            throw new TireSKUAlreadyExistsException("Tire with SKU " + tireDTO.getTireSku() + " already exists");
        }

        boolean result = tireRepository.updateTire(tireDTO);

        if (!result) {
            logger.error("Failed to update tire with id: {}", tireDTO.getId());
            throw new RuntimeException("Failed to update tire with id: " + tireDTO.getId());
        }
        logger.info("Tire [{}] updated successfully", tireDTO.getId());

        return tireRepository.selectTireById(tireDTO.getId())
                .orElseThrow(() -> new ProductNotFoundException("Failed to fetch tire after insertion"));
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
        Set<ConstraintViolation<TireDTO>> violations = validator.validate(tireDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation failed for TireDTO", violations);
        }
    }

    private void validateTireRequestDTO(TireRequestDTO tireRequestDTO) {
        Set<ConstraintViolation<TireRequestDTO>> violations = validator.validate(tireRequestDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation failed for TireRequestDTO", violations);
        }
    }
}
