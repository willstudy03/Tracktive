package com.tracktive.userservice.repository;

import com.tracktive.userservice.model.DTO.CourierDTO;
import com.tracktive.userservice.model.entity.Courier;
import com.tracktive.userservice.model.entity.Retailer;

import java.util.List;
import java.util.Optional;

/**
* Description: Courier Repository Interface
* @author William Theo
* @date 2/3/2025
*/
public interface CourierRepository {
    // Get all courier
    List<CourierDTO> selectAllCouriers();
    // Find courier by ID
    Optional<CourierDTO> selectCourierById(String id);
    // Lock a courier by ID
    Optional<CourierDTO> lockCourierById(String id);
    // Insert a new courier
    boolean addCourier(CourierDTO courierDTO);
    // Update courier info
    boolean updateCourier(CourierDTO courierDTO);
    // Delete courier by ID
    boolean deleteById(String id);
}
