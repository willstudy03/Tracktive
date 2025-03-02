package com.tracktive.userservice.repository;

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
    List<Retailer> selectAllRetailers();
    // Find retailer by ID
    Optional<Retailer> selectRetailerById(String id);
    // Lock a retailer by ID
    Optional<Retailer> lockRetailerById(String id);
    // Insert a new retailer
    boolean addRetailer(Retailer retailer);
    // Update retailer info
    boolean updateRetailer(Retailer retailer);
    // Delete retailer by ID
    boolean deleteById(String id);
}
