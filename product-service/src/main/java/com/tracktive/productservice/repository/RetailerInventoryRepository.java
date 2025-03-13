package com.tracktive.productservice.repository;

import com.tracktive.productservice.model.DTO.RetailerInventoryDTO;

import java.util.List;
import java.util.Optional;

/**
* Description: Retailer Inventory Repository Interface
* @author William Theo
* @date 10/3/2025
*/
public interface RetailerInventoryRepository {

    // Select all retailer product
    List<RetailerInventoryDTO> selectAllRetailerInventory();

    // Select retailer inventory by retailer id
    List<RetailerInventoryDTO> selectRetailerInventoryByRetailerId(String retailerId);

    // Select retailer inventory by its id
    Optional<RetailerInventoryDTO> selectRetailerInventoryById(String id);

    // Lock operation
    Optional<RetailerInventoryDTO> lockRetailerInventoryById(String id);

    // Insert operation
    boolean addRetailerInventory(RetailerInventoryDTO retailerInventoryDTO);

    // Update operation
    boolean updateRetailerInventory(RetailerInventoryDTO retailerInventoryDTO);

    // Delete operation
    boolean deleteRetailerInventoryById(String id);
}
