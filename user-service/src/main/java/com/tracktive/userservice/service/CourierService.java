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
    boolean addCourier(CourierDTO courierDTO);
    boolean updateCourier(CourierDTO courierDTO);
    boolean deleteCourierById(String id);
}
