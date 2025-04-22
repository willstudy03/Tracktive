package com.tracktive.userservice.service;

import com.tracktive.userservice.model.DTO.SupplierDTO;

import java.util.List;

/**
 * Description: Retailer CRUD Service Interface
 * @author William Theo
 * @date 5/3/2025
 */
public interface SupplierService {
    List<SupplierDTO> selectAllSuppliers();
    SupplierDTO selectSupplierById(String id);
    SupplierDTO lockSupplierById(String id);
    SupplierDTO addSupplier(SupplierDTO supplierDTO);
    SupplierDTO updateSupplier(SupplierDTO supplierDTO);
    void deleteSupplierById(String id);
}
