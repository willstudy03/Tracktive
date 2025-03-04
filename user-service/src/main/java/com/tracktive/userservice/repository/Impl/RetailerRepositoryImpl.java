package com.tracktive.userservice.repository.Impl;

import com.tracktive.userservice.model.DAO.RetailerDAO;
import com.tracktive.userservice.model.DTO.RetailerDTO;
import com.tracktive.userservice.model.DTO.SupplierDTO;
import com.tracktive.userservice.model.entity.Retailer;
import com.tracktive.userservice.repository.RetailerRepository;
import com.tracktive.userservice.util.RetailerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* Description: Retailer Repository Implementation
* @author William Theo
* @date 2/3/2025
*/
@Repository
public class RetailerRepositoryImpl implements RetailerRepository {

    private final RetailerDAO retailerDAO;

    @Autowired
    RetailerRepositoryImpl(RetailerDAO retailerDAO){
        this.retailerDAO = retailerDAO;
    }

    @Override
    public List<RetailerDTO> selectAllRetailers() {
        return Optional.ofNullable(retailerDAO.selectAllRetailers())
                .orElse(Collections.emptyList())
                .stream()
                .map(RetailerConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RetailerDTO> selectRetailerById(String id) {
        validateId(id);
        return retailerDAO.selectRetailerById(id).map(RetailerConverter::toDTO);
    }

    @Override
    public Optional<RetailerDTO> lockRetailerById(String id) {
        validateId(id);
        return retailerDAO.lockRetailerById(id).map(RetailerConverter::toDTO);
    }

    @Override
    public boolean addRetailer(RetailerDTO retailerDTO) {
        validateRetailerDTO(retailerDTO);
        Retailer retailer = RetailerConverter.toEntity(retailerDTO);
        return retailerDAO.addRetailer(retailer) > 0;
    }

    @Override
    public boolean updateRetailer(RetailerDTO retailerDTO) {
        validateRetailerDTO(retailerDTO);
        Retailer retailer = RetailerConverter.toEntity(retailerDTO);
        return retailerDAO.updateRetailer(retailer) > 0;
    }

    @Override
    public boolean deleteById(String id) {
        validateId(id);
        return retailerDAO.deleteRetailerById(id) > 0;
    }

    private void validateId(String id){
        if (Objects.isNull(id) || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
    }

    private void validateRetailerDTO(RetailerDTO retailerDTO) {
        if (Objects.isNull(retailerDTO)) {
            throw new IllegalArgumentException("RetailerDTO cannot be null");
        }
    }
}
