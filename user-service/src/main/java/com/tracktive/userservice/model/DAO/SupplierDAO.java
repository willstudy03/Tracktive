package com.tracktive.userservice.model.DAO;

import com.tracktive.userservice.model.entity.Supplier;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Optional;

/**
* Description: Supplier DAO for mybatis mapping
* @author William Theo
* @date 2/3/2025
*/
@Mapper
public interface SupplierDAO {

    // Select operations
    List<Supplier> selectAllSuppliers();
    Optional<Supplier> selectSupplierById(String id);
    // Lock operation
    Optional<Supplier> lockSupplierById(String id);
    // Insert operation
    int addSupplier(Supplier supplier);
    // Update operation
    int updateSupplier(Supplier supplier);
    // Delete operation
    int deleteSupplierById(String id);
}
