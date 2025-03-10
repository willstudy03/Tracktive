package com.tracktive.productservice.repository;

import com.tracktive.productservice.model.DTO.TireDTO;

import java.util.List;
import java.util.Optional;

/**
* Description: Tire Repository Interface
* @author William Theo
* @date 9/3/2025
*/
public interface TireRepository {
    // Get all Tires
    List<TireDTO> selectAllTire();
    // Find all Tires by Params
    List<TireDTO> selectTireByParams(TireDTO tireDTO);
    // Find Tire by ID
    Optional<TireDTO> selectTireById(String id);
    // Lock a Tire by ID
    Optional<TireDTO> lockTireById(String id);
    // Find Tire by SKU
    Optional<TireDTO> selectTireBySKU(String sku);
    // Insert a new tire
    boolean addTire(TireDTO tireDTO);
    // Update tire info
    boolean updateTire(TireDTO tireDTO);
    // Delete tire by ID
    boolean deleteById(String id);
}
