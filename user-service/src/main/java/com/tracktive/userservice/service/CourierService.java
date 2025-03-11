package com.tracktive.userservice.service;

import com.tracktive.userservice.model.DTO.CourierDTO;
import com.tracktive.userservice.model.DTO.RetailerDTO;

import java.util.List;

/**
 * Description: Courier CRUD Service Interface
 * @author William Theo
 * @date 5/3/2025
 */
public interface CourierService {
    List<CourierDTO> selectAllCouriers();
    CourierDTO selectCourierById(String id);
    CourierDTO lockCourierById(String id);
    void addCourier(CourierDTO courierDTO);
    void updateCourier(CourierDTO courierDTO);
    void deleteCourierById(String id);
}
