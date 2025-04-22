package com.tracktive.userservice.repository.Impl;

import com.tracktive.userservice.exception.DatabaseOperationException;
import com.tracktive.userservice.exception.ForeignKeyConstraintException;
import com.tracktive.userservice.exception.UserAlreadyExistsException;
import com.tracktive.userservice.model.DAO.RetailerDAO;
import com.tracktive.userservice.model.DTO.RetailerDTO;
import com.tracktive.userservice.model.entity.Retailer;
import com.tracktive.userservice.repository.RetailerRepository;
import com.tracktive.userservice.util.converter.RetailerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collections;
import java.util.List;
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
        return retailerDAO.selectRetailerById(id).map(RetailerConverter::toDTO);
    }

    @Override
    public Optional<RetailerDTO> lockRetailerById(String id) {
        return retailerDAO.lockRetailerById(id).map(RetailerConverter::toDTO);
    }

    @Override
    public boolean addRetailer(RetailerDTO retailerDTO) {
        try {
            Retailer retailer = RetailerConverter.toEntity(retailerDTO);
            return retailerDAO.addRetailer(retailer) > 0;
        } catch (DuplicateKeyException e) {
            throw new UserAlreadyExistsException("Retailer with id " + retailerDTO.getRetailerId() + " already exists", e);
        } catch (DataIntegrityViolationException e) {
            Throwable rootCause = e.getRootCause(); // Get the root cause
            if (rootCause instanceof SQLIntegrityConstraintViolationException) {
                throw new ForeignKeyConstraintException("User with ID " + retailerDTO.getRetailerId() + " does not exist.", e);
            }
            throw new DatabaseOperationException("Database integrity constraint violation occurred.", e);
        } catch (DataAccessException e) {
            throw new DatabaseOperationException("Failed to add retailer to the database", e);
        }
    }

    @Override
    public boolean updateRetailer(RetailerDTO retailerDTO) {
        Retailer retailer = RetailerConverter.toEntity(retailerDTO);
        return retailerDAO.updateRetailer(retailer) > 0;
    }

    @Override
    public boolean deleteById(String id) {
        return retailerDAO.deleteRetailerById(id) > 0;
    }

}
