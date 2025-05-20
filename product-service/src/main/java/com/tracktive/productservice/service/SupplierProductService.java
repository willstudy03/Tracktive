package com.tracktive.productservice.service;

import com.tracktive.productservice.model.DTO.SupplierProductDTO;
import com.tracktive.productservice.model.DTO.SupplierProductRequestDTO;
import com.tracktive.productservice.model.VO.SupplierProductVO;

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
    List<SupplierProductVO> selectSupplierProductsBySupplierId(String supplierId);

    // Select supplier product by its id
    SupplierProductDTO selectSupplierProductById(String id);

    // Lock operation
    SupplierProductDTO lockSupplierProductById(String id);

    // Insert operation
    SupplierProductDTO addSupplierProduct(SupplierProductRequestDTO supplierProductRequestDTO);

    // Update operation
    SupplierProductDTO updateSupplierProduct(SupplierProductDTO supplierProductDTO);

    SupplierProductDTO deductSupplierProductStock(String supplierProductId, int quantity);

    // Delete operation
    void deleteSupplierProductById(String id);
}
