package com.tracktive.productservice.repository;

import com.tracktive.productservice.model.DTO.SupplierProductDTO;
import com.tracktive.productservice.model.entity.SupplierProduct;

import java.util.List;
import java.util.Optional;

/**
* Description: Supplier Product Repository Interface
* @author William Theo
* @date 10/3/2025
*/
public interface SupplierProductRepository {

    // Select all supplier products
    List<SupplierProductDTO> selectAllSupplierProducts();

    // Select supplier products by supplier id
    List<SupplierProductDTO> selectSupplierProductsBySupplierId(String supplierId);

    // Select supplier product by its id
    Optional<SupplierProductDTO> selectSupplierProductById(String id);

    // Lock operation
    Optional<SupplierProductDTO> lockSupplierProductById(String id);

    // Insert operation
    boolean addSupplierProduct(SupplierProductDTO supplierProductDTO);

    // Update operation
    boolean updateSupplierProduct(SupplierProductDTO supplierProductDTO);

    // Delete operation
    boolean deleteSupplierProductById(String id);
}
