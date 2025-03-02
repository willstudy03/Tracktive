package com.tracktive.userservice.repository;

import com.tracktive.userservice.model.entity.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierRepository {
    // Get all suppliers
    List<Supplier> selectAllSuppliers();
    // Find supplier by ID
    Optional<Supplier> selectSupplierById(String id);
    // Lock a Supplier by ID
    Optional<Supplier> lockUserById(String id);
    // Insert a new supplier
    boolean addSupplier(Supplier supplier);
    // Update supplier info
    boolean updateSupplier(Supplier supplier);
    // Delete supplier by ID
    boolean deleteById(String id);
}
