package com.tracktive.userservice.repository;

import com.tracktive.userservice.model.DTO.SupplierDTO;

import java.util.List;
import java.util.Optional;

public interface SupplierRepository {
    // Get all suppliers
    List<SupplierDTO> selectAllSuppliers();
    // Find supplier by ID
    Optional<SupplierDTO> selectSupplierById(String id);
    // Lock a Supplier by ID
    Optional<SupplierDTO> lockSupplierById(String id);
    // Insert a new supplier
    boolean addSupplier(SupplierDTO supplierDTO);
    // Update supplier info
    boolean updateSupplier(SupplierDTO supplierDTO);
    // Delete supplier by ID
    boolean deleteById(String id);
}
