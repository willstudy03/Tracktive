package com.tracktive.productservice.service;

import com.tracktive.productservice.model.DTO.TireDTO;
import com.tracktive.productservice.model.DTO.TireRequestDTO;

import java.util.List;
import java.util.Optional;

public interface TireService {
    // Get all Tires
    List<TireDTO> selectAllTire();
    // Find all Tires by Params
    List<TireDTO> selectTireByParams(TireDTO tireDTO);
    // Find Tire by ID
    TireDTO selectTireById(String id);
    // Lock a Tire by ID
    TireDTO lockTireById(String id);
    // Find Tire by SKU
    TireDTO selectTireBySKU(String sku);
    // Insert a new tire
    TireDTO addTire(TireRequestDTO tireRequestDTO);
    // Update tire info
    TireDTO updateTire(TireDTO tireDTO);
    // Delete tire by ID
    void deleteById(String id);
}
