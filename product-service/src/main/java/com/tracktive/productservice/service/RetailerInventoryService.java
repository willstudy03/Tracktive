package com.tracktive.productservice.service;

import com.tracktive.productservice.model.DTO.RetailerInventoryDTO;
import com.tracktive.productservice.model.DTO.RetailerInventoryRequestDTO;
import com.tracktive.productservice.model.VO.RetailerInventoryVO;

import java.util.List;

/**
* Description: Retailer Inventory Service Interface
* @author William Theo
* @date 13/3/2025
*/
public interface RetailerInventoryService {
    // Select all retailer product
    List<RetailerInventoryDTO> selectAllRetailerInventory();

    // Select retailer inventory by retailer id
    List<RetailerInventoryVO> selectRetailerInventoryByRetailerId(String retailerId);

    // Select retailer inventory by its id
    RetailerInventoryDTO selectRetailerInventoryById(String id);

    // Lock operation
    RetailerInventoryDTO lockRetailerInventoryById(String id);

    // Insert operation
    RetailerInventoryDTO addRetailerInventory(RetailerInventoryRequestDTO retailerInventoryRequestDTO);

    // Update operation
    RetailerInventoryDTO updateRetailerInventory(RetailerInventoryDTO retailerInventoryDTO);

    // Delete operation
    void deleteRetailerInventoryById(String id);
}
