package com.tracktive.deliveryservice.service.Impl;

import com.tracktive.deliveryservice.exception.DeliveryTaskNotFoundException;
import com.tracktive.deliveryservice.exception.LockAcquisitionException;
import com.tracktive.deliveryservice.model.DTO.DeliveryTaskDTO;
import com.tracktive.deliveryservice.model.DTO.DeliveryTaskRequestDTO;
import com.tracktive.deliveryservice.repository.DeliveryTaskRepository;
import com.tracktive.deliveryservice.service.DeliveryTaskService;
import com.tracktive.deliveryservice.util.converter.DeliveryTaskConverter;
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
* Description: Delivery Task Service Implementation
* @author William Theo
* @date 21/3/2025
*/
@Service
public class DeliveryTaskServiceImpl implements DeliveryTaskService {

    private final DeliveryTaskRepository deliveryTaskRepository;

    private final Validator validator;

    private static final Logger logger = LoggerFactory.getLogger(DeliveryTaskServiceImpl.class);

    @Autowired
    public DeliveryTaskServiceImpl(DeliveryTaskRepository deliveryTaskRepository, Validator validator) {
        this.deliveryTaskRepository = deliveryTaskRepository;
        this.validator = validator;
    }

    @Override
    public List<DeliveryTaskDTO> selectAllDeliveryTasks() {
        return deliveryTaskRepository.selectAllDeliveryTasks();
    }

    @Override
    public List<DeliveryTaskDTO> selectAllDeliveryTasksByCourierId(String id) {
        validateCourierId(id);
        return deliveryTaskRepository.selectAllDeliveryTasksByCourierId(id);
    }

    @Override
    public DeliveryTaskDTO selectDeliveryTaskByOrderId(String id) {
        validateOrderId(id);
        return deliveryTaskRepository.selectDeliveryTaskByOrderId(id)
                .orElseThrow(() -> {
                    logger.warn("Delivery Task not found with Order id: {}", id);
                    return new DeliveryTaskNotFoundException("Delivery Task not found with Order id: " + id);
                });
    }

    @Override
    public DeliveryTaskDTO selectDeliveryTaskById(String id) {
        validateId(id);
        return deliveryTaskRepository.selectDeliveryTaskById(id)
                .orElseThrow(() -> {
                    logger.warn("Delivery Task not found with id: {}", id);
                    return new DeliveryTaskNotFoundException("Delivery Task not found with id: " + id);
                });
    }

    @Override
    @Transactional
    public DeliveryTaskDTO lockDeliveryTaskById(String id) {
        validateId(id);
        try {
            return deliveryTaskRepository.lockDeliveryTaskById(id)
                    .orElseThrow(() -> {
                        logger.warn("Failed to lock delivery task, delivery task not found with id: {}", id);
                        return new DeliveryTaskNotFoundException("Delivery Task not found with id: " + id);
                    });
        } catch (PersistenceException e) {
            logger.error("Persistence error occurred during lock acquisition for delivery task id: {}", id, e);
            throw new LockAcquisitionException("Failed to acquire lock for delivery task with id: " + id, e);
        } catch (Exception e) {
            logger.error("Unexpected error during delivery task lock for id: {}", id, e);
            throw new RuntimeException("Unexpected error during lock operation", e);
        }
    }

    @Override
    @Transactional
    public DeliveryTaskDTO addDeliveryTask(DeliveryTaskRequestDTO deliveryTaskRequestDTO) {

        validateDeliveryTaskRequestDTO(deliveryTaskRequestDTO);

        DeliveryTaskDTO deliveryTaskDTO = DeliveryTaskConverter.toDTO(deliveryTaskRequestDTO);

        boolean result = deliveryTaskRepository.addDeliveryTask(deliveryTaskDTO);
        if (!result) {
            logger.error("Failed to add delivery task with id: {}", deliveryTaskDTO.getId());
            throw new RuntimeException("Failed to add delivery task with id: " + deliveryTaskDTO.getId());
        }
        logger.info("Delivery Task [{}] added successfully", deliveryTaskDTO.getId());

        return deliveryTaskRepository.selectDeliveryTaskById(deliveryTaskDTO.getId())
                .orElseThrow(() -> new DeliveryTaskNotFoundException("Failed to find delivery task after insertion"));
    }

    @Override
    @Transactional
    public DeliveryTaskDTO updateDeliveryTask(DeliveryTaskDTO deliveryTaskDTO) {

        validateDeliveryTaskDTO(deliveryTaskDTO);

        boolean result = deliveryTaskRepository.updateDeliveryTask(deliveryTaskDTO);
        if (!result) {
            logger.error("Failed to update delivery task with id: {}", deliveryTaskDTO.getId());
            throw new DeliveryTaskNotFoundException("Failed to update delivery task with id: " + deliveryTaskDTO.getId());
        }
        logger.info("Delivery Task [{}] updated successfully", deliveryTaskDTO.getId());

        return deliveryTaskRepository.selectDeliveryTaskById(deliveryTaskDTO.getId())
                .orElseThrow(() -> new DeliveryTaskNotFoundException("Failed to find delivery task after insertion"));
    }

    @Override
    @Transactional
    public void deleteDeliveryTaskById(String id) {
        validateId(id);
        boolean result = deliveryTaskRepository.deleteDeliveryTaskById(id);
        if (!result) {
            logger.error("Failed to delete delivery task with id: {}", id);
            throw new DeliveryTaskNotFoundException("Failed to delete delivery task with id: " + id);
        }
        logger.info("Delivery Task [{}] deleted successfully", id);
    }

    private void validateCourierId(String id) {
        if (Objects.isNull(id) || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Courier ID cannot be null or empty");
        }
    }

    private void validateOrderId(String id) {
        if (Objects.isNull(id) || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Order ID cannot be null or empty");
        }
    }

    private void validateId(String id) {
        if (Objects.isNull(id) || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Delivery Task ID cannot be null or empty");
        }
    }

    private void validateDeliveryTaskDTO(DeliveryTaskDTO deliveryTaskDTO) {
        Set<ConstraintViolation<DeliveryTaskDTO>> violations = validator.validate(deliveryTaskDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation failed for Delivery Task DTO", violations);
        }
    }

    private void validateDeliveryTaskRequestDTO(DeliveryTaskRequestDTO deliveryTaskRequestDTO) {
        Set<ConstraintViolation<DeliveryTaskRequestDTO>> violations = validator.validate(deliveryTaskRequestDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation failed for Delivery Task Request DTO", violations);
        }
    }
}
