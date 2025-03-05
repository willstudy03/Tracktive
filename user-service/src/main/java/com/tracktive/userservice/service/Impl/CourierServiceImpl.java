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
        return courierRepository.selectCourierById(id)
                .orElseThrow(()->new UserNotFoundException("Courier not found with id: " + id));
    }

    @Override
    @Transactional
    public CourierDTO lockCourierById(String id) {
        try{
            return courierRepository.lockCourierById(id)
                    .orElseThrow(()->new UserNotFoundException("Courier not found with id: " + id));
        }catch (CannotAcquireLockException e){
            logger.warn("Lock acquisition failed for courier id: {}", id);
            throw new LockAcquisitionException("Failed to acquire lock for courier with id: " + id, e);
        }
    }

    @Override
    @Transactional
    public boolean addCourier(CourierDTO courierDTO) {
        return courierRepository.addCourier(courierDTO);
    }

    @Override
    @Transactional
    public boolean updateCourier(CourierDTO courierDTO) {
        boolean updated = courierRepository.updateCourier(courierDTO);
        if (!updated) {
            logger.info("Failed to update: Courier not found with id: {}", courierDTO.getCourierId());
            throw new UserNotFoundException("Failed to update: Courier not found with id: " + courierDTO.getCourierId());
        }
        return true;
    }

    @Override
    @Transactional
    public boolean deleteCourierById(String id) {
        boolean deleted = courierRepository.deleteById(id);
        if (!deleted) {
            logger.info("Failed to delete: Courier not found with id: {}", id);
            throw new UserNotFoundException("Failed to delete: Courier not found with id: " + id);
        }
        return true;
    }
}
