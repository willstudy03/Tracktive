package com.tracktive.productservice.model.DAO;

import com.tracktive.productservice.model.entity.RetailerInventory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

/**
* Description: Retailer Inventory DAO for mybatis mapping
* @author William Theo
* @date 10/3/2025
*/
@Mapper
public interface RetailerInventoryDAO {
    // Select all retailer product
    List<RetailerInventory> selectAllRetailerInventory();

    // Select retailer inventory by retailer id
    List<RetailerInventory> selectRetailerInventoryByRetailerId(String retailerId);

    // Select retailer inventory by its id
    Optional<RetailerInventory> selectRetailerInventoryById(String id);

    // Lock operation
    Optional<RetailerInventory> lockRetailerInventoryById(String id);

    // Insert operation
    int addRetailerInventory(RetailerInventory retailerInventory);

    // Update operation
    int updateRetailerInventory(RetailerInventory retailerInventory);

    // Delete operation
    int deleteRetailerInventoryById(String id);
}
