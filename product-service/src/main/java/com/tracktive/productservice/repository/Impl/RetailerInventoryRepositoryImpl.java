package com.tracktive.productservice.repository.Impl;

import com.tracktive.productservice.model.DAO.RetailerInventoryDAO;
import com.tracktive.productservice.model.DTO.RetailerInventoryDTO;
import com.tracktive.productservice.model.entity.RetailerInventory;
import com.tracktive.productservice.repository.RetailerInventoryRepository;
import com.tracktive.productservice.util.converter.Impl.RetailerInventoryConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
* Description: Retailer Inventory Repository Implementation
* @author William Theo
* @date 10/3/2025
*/
@Repository
public class RetailerInventoryRepositoryImpl implements RetailerInventoryRepository {

    private final RetailerInventoryDAO retailerInventoryDAO;

    @Autowired
    public RetailerInventoryRepositoryImpl(RetailerInventoryDAO retailerInventoryDAO) {
        this.retailerInventoryDAO = retailerInventoryDAO;
    }

    @Override
    public List<RetailerInventoryDTO> selectAllRetailerInventory() {
        return retailerInventoryDAO.selectAllRetailerInventory()
                .stream()
                .map(RetailerInventoryConverter::toDTO)
                .toList();
    }

    @Override
    public List<RetailerInventoryDTO> selectRetailerInventoryByRetailerId(String retailerId) {
        return retailerInventoryDAO.selectRetailerInventoryByRetailerId(retailerId)
                .stream()
                .map(RetailerInventoryConverter::toDTO)
                .toList();
    }

    @Override
    public Optional<RetailerInventoryDTO> selectRetailerInventoryById(String id) {
        return retailerInventoryDAO.selectRetailerInventoryById(id)
                .map(RetailerInventoryConverter::toDTO);
    }

    @Override
    public Optional<RetailerInventoryDTO> lockRetailerInventoryById(String id) {
        return retailerInventoryDAO.lockRetailerInventoryById(id)
                .map(RetailerInventoryConverter::toDTO);
    }

    @Override
    public boolean addRetailerInventory(RetailerInventoryDTO retailerInventoryDTO) {
        RetailerInventory retailerInventory = RetailerInventoryConverter.toEntity(retailerInventoryDTO);
        return retailerInventoryDAO.addRetailerInventory(retailerInventory) > 0;
    }

    @Override
    public boolean updateRetailerInventory(RetailerInventoryDTO retailerInventoryDTO) {
        RetailerInventory retailerInventory = RetailerInventoryConverter.toEntity(retailerInventoryDTO);
        return retailerInventoryDAO.updateRetailerInventory(retailerInventory) > 0;
    }

    @Override
    public boolean deleteRetailerInventoryById(String id) {
        return retailerInventoryDAO.deleteRetailerInventoryById(id) > 0;
    }

}
