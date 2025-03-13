package com.tracktive.productservice.service;

import com.tracktive.productservice.model.DTO.SupplierProductDTO;

import java.util.List;

/**
* Description: Supplier Product Service Interface
* @author William Theo
* @date 13/3/2025
*/
public interface SupplierProductService {
    // Select all supplier products
    List<SupplierProductDTO> selectAllSupplierProducts();

    // Select supplier products by supplier id
    List<SupplierProductDTO> selectSupplierProductsBySupplierId(String supplierId);

    // Select supplier product by its id
    SupplierProductDTO selectSupplierProductById(String id);

    // Lock operation
    SupplierProductDTO lockSupplierProductById(String id);

    // Insert operation
    void addSupplierProduct(SupplierProductDTO supplierProductDTO);

    // Update operation
    void updateSupplierProduct(SupplierProductDTO supplierProductDTO);

    // Delete operation
    void deleteSupplierProductById(String id);
}
