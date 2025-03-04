package com.tracktive.userservice.repository;

import com.tracktive.userservice.model.DTO.RetailerDTO;
import com.tracktive.userservice.model.entity.Retailer;

import java.util.List;
import java.util.Optional;

/**
* Description: Retailer Repository Interface
* @author William Theo
* @date 2/3/2025
*/
public interface RetailerRepository {
    // Get all retailer
    List<RetailerDTO> selectAllRetailers();
    // Find retailer by ID
    Optional<RetailerDTO> selectRetailerById(String id);
    // Lock a retailer by ID
    Optional<RetailerDTO> lockRetailerById(String id);
    // Insert a new retailer
    boolean addRetailer(RetailerDTO retailerDTO);
    // Update retailer info
    boolean updateRetailer(RetailerDTO retailerDTO);
    // Delete retailer by ID
    boolean deleteById(String id);
}
