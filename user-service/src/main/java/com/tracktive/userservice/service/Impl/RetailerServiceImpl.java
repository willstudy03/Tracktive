package com.tracktive.userservice.service.Impl;

import com.tracktive.userservice.exception.LockAcquisitionException;
import com.tracktive.userservice.exception.UserNotFoundException;
import com.tracktive.userservice.model.DTO.RetailerDTO;
import com.tracktive.userservice.repository.RetailerRepository;
import com.tracktive.userservice.service.RetailerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * Description: Retailer CRUD Service Implementation
 * @author William Theo
 * @date 5/3/2025
 */
@Service
public class RetailerServiceImpl implements RetailerService {

    private final RetailerRepository retailerRepository;

    private static final Logger logger = LoggerFactory.getLogger(RetailerServiceImpl.class);

    @Autowired
    public RetailerServiceImpl(RetailerRepository retailerRepository) {
        this.retailerRepository = retailerRepository;
    }

    @Override
    public List<RetailerDTO> selectAllRetailers() {
        return retailerRepository.selectAllRetailers();
    }

    @Override
    public RetailerDTO selectRetailerById(String id) {
        return retailerRepository.selectRetailerById(id)
                .orElseThrow(()->new UserNotFoundException("Retailer not found with id: " + id));
    }

    @Override
    @Transactional
    public RetailerDTO lockRetailerById(String id) {
        try{
            return retailerRepository.lockRetailerById(id)
                    .orElseThrow(()->new UserNotFoundException("Retailer not found with id: " + id));
        }catch (CannotAcquireLockException e){
            logger.warn("Lock acquisition failed for retailer id: {}", id);
            throw new LockAcquisitionException("Failed to acquire lock for retailer with id: " + id, e);
        }
    }

    @Override
    @Transactional
    public boolean addRetailer(RetailerDTO retailerDTO) {
        return retailerRepository.addRetailer(retailerDTO);
    }

    @Override
    @Transactional
    public boolean updateRetailer(RetailerDTO retailerDTO) {
        boolean updated = retailerRepository.updateRetailer(retailerDTO);
        if (!updated) {
            logger.info("Failed to update: Retailer not found with id: {}", retailerDTO.getRetailerId());
            throw new UserNotFoundException("Failed to update: Retailer not found with id: " + retailerDTO.getRetailerId());
        }
        return true;
    }

    @Override
    @Transactional
    public boolean deleteRetailerById(String id) {
        boolean deleted = retailerRepository.deleteById(id);
        if (!deleted) {
            logger.info("Failed to delete: Retailer not found with id: {}", id);
            throw new UserNotFoundException("Failed to delete: Retailer not found with id: " + id);
        }
        return true;
    }
}
