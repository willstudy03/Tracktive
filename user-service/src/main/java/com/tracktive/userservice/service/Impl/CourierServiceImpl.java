package com.tracktive.userservice.service.Impl;

import com.tracktive.userservice.exception.LockAcquisitionException;
import com.tracktive.userservice.exception.UserNotFoundException;
import com.tracktive.userservice.model.DTO.CourierDTO;
import com.tracktive.userservice.repository.CourierRepository;
import com.tracktive.userservice.service.CourierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * Description: Courier CRUD Service Implementation
 * @author William Theo
 * @date 5/3/2025
 */
@Service
public class CourierServiceImpl implements CourierService {

    private final CourierRepository courierRepository;

    private static final Logger logger = LoggerFactory.getLogger(CourierServiceImpl.class);

    @Autowired
    public CourierServiceImpl(CourierRepository courierRepository) {
        this.courierRepository = courierRepository;
    }

    @Override
    public List<CourierDTO> selectAllCouriers() {
        return courierRepository.selectAllCouriers();
    }

    @Override
    public CourierDTO selectCourierById(String id) {
        validateId(id);
        return courierRepository.selectCourierById(id)
                .orElseThrow(()->{
                    logger.warn("Courier not found with id: {}", id);
                    return new UserNotFoundException("Courier not found with id: " + id);
                });
    }

    @Override
    @Transactional
    public CourierDTO lockCourierById(String id) {
        validateId(id);
        try {
            return courierRepository.lockCourierById(id)
                    .orElseThrow(() -> {
                        logger.warn("Failed to lock courier, courier not found with id: {}", id);
                        return new UserNotFoundException("Courier not found with id: " + id);
                    });
        } catch (CannotAcquireLockException e) {
            logger.error("Lock acquisition failed for courier id: {}", id, e);
            throw new LockAcquisitionException("Failed to acquire lock for courier with id: " + id, e);
        } catch (Exception e) {
            logger.error("Unexpected error during courier lock for id: {}", id, e);
            throw new RuntimeException("Unexpected error during lock operation", e);
        }
    }

    @Override
    @Transactional
    public void addCourier(CourierDTO courierDTO) {
        validateCourierDTO(courierDTO);
        boolean result = courierRepository.addCourier(courierDTO);
        if (!result) {
            logger.error("Failed to add courier with id: {}", courierDTO.getCourierId());
            throw new RuntimeException("Failed to add courier with id: " + courierDTO.getCourierId());
        }
        logger.info("Courier [{}] added successfully", courierDTO.getCourierId());
    }

    @Override
    @Transactional
    public void updateCourier(CourierDTO courierDTO) {
        validateCourierDTO(courierDTO);
        boolean result = courierRepository.updateCourier(courierDTO);
        if (!result) {
            logger.error("Failed to update courier with id: {}", courierDTO.getCourierId());
            throw new UserNotFoundException("Failed to update courier with id: " + courierDTO.getCourierId());
        }
        logger.info("Courier [{}] updated successfully", courierDTO.getCourierId());
    }

    @Override
    @Transactional
    public void deleteCourierById(String id) {
        validateId(id);
        boolean result = courierRepository.deleteById(id);
        if (!result) {
            logger.error("Failed to delete courier with id: {}", id);
            throw new UserNotFoundException("Failed to delete courier with id: " + id);
        }
        logger.info("Courier [{}] deleted successfully", id);
    }

    private void validateId(String id){
        if (Objects.isNull(id) || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
    }

    private void validateCourierDTO(CourierDTO courierDTO) {
        if (Objects.isNull(courierDTO)) {
            throw new IllegalArgumentException("CourierDTO cannot be null");
        }
        //TODO: VALIDATION
    }
}
