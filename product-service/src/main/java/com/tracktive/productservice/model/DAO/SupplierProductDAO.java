package com.tracktive.productservice.model.DAO;

import com.tracktive.productservice.model.entity.SupplierProduct;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

/**
* Description: Supplier Product DAO for mybatis mapping
* @author William Theo
* @date 10/3/2025
*/
@Mapper
public interface SupplierProductDAO {

    // Select all supplier products
    List<SupplierProduct> selectAllSupplierProducts();

    // Select supplier products by supplier id
    List<SupplierProduct> selectSupplierProductsBySupplierId(String supplierId);

    // Select supplier product by its id
    Optional<SupplierProduct> selectSupplierProductById(String id);

    // Lock operation
    Optional<SupplierProduct> lockSupplierProductById(String id);

    // Insert operation
    int addSupplierProduct(SupplierProduct supplierProduct);

    // Update operation
    int updateSupplierProduct(SupplierProduct supplierProduct);

    // Delete operation
    int deleteSupplierProductById(String id);
}
