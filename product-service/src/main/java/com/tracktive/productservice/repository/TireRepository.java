package com.tracktive.productservice.repository;

import com.tracktive.productservice.model.entity.Tire;
import java.util.List;
import java.util.Optional;

/**
* Description: Tire Repository Interface
* @author William Theo
* @date 9/3/2025
*/
public interface TireRepository {
    // Get all Tires
    List<Tire> selectAllTire();
    // Find all Tires by Params
    List<Tire> selectTireByParams(Tire tire);
    // Find Tire by ID
    Optional<Tire> selectTireById(String id);
    // Lock a Tire by ID
    Optional<Tire> lockTireById(String id);
    // Find Tire by SKU
    Optional<Tire> selectTireBySKU(String sku);
    // Insert a new tire
    boolean addTire(Tire tire);
    // Update tire info
    boolean updateTire(Tire tire);
    // Delete tire by ID
    boolean deleteById(String id);
}
