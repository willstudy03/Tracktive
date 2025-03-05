package com.tracktive.userservice.service.Impl;

import com.tracktive.userservice.exception.LockAcquisitionException;
import com.tracktive.userservice.exception.UserNotFoundException;
import com.tracktive.userservice.model.DTO.SupplierDTO;
import com.tracktive.userservice.repository.SupplierRepository;
import com.tracktive.userservice.service.SupplierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description: Supplier CRUD Service Implementation
 * @author William Theo
 * @date 5/3/2025
 */
@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    private static final Logger logger = LoggerFactory.getLogger(SupplierServiceImpl.class);

    @Override
    public List<SupplierDTO> selectAllSuppliers() {
        return supplierRepository.selectAllSuppliers();
    }

    @Override
    public SupplierDTO selectSupplierById(String id) {
        return supplierRepository.selectSupplierById(id)
                .orElseThrow(()->new UserNotFoundException("Supplier not found with id: " + id));
    }

    @Override
    @Transactional
    public SupplierDTO lockSupplierById(String id) {
        try{
            return supplierRepository.lockSupplierById(id)
                    .orElseThrow(()->new UserNotFoundException("Supplier not found with id: " + id));
        }catch (CannotAcquireLockException e){
            logger.warn("Lock acquisition failed for supplier id: {}", id);
            throw new LockAcquisitionException("Failed to acquire lock for supplier with id: " + id, e);
        }
    }

    @Override
    @Transactional
    public boolean addSupplier(SupplierDTO supplierDTO) {
        return supplierRepository.addSupplier(supplierDTO);
    }

    @Override
    @Transactional
    public boolean updateSupplier(SupplierDTO supplierDTO) {
        boolean updated = supplierRepository.updateSupplier(supplierDTO);
        if (!updated) {
            logger.info("Failed to update: Supplier not found with id: {}", supplierDTO.getSupplierId());
            throw new UserNotFoundException("Failed to update: Supplier not found with id: " + supplierDTO.getSupplierId());
        }
        return true;
    }

    @Override
    @Transactional
    public boolean deleteSupplierById(String id) {
        boolean deleted = supplierRepository.deleteById(id);
        if (!deleted) {
            logger.info("Failed to delete: Supplier not found with id: {}", id);
            throw new UserNotFoundException("Failed to delete: Supplier not found with id: " + id);
        }
        return true;
    }
}
