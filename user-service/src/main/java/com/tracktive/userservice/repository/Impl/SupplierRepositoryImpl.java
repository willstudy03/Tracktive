package com.tracktive.userservice.repository.Impl;

import com.tracktive.userservice.exception.DatabaseOperationException;
import com.tracktive.userservice.exception.UserAlreadyExistsException;
import com.tracktive.userservice.model.DAO.SupplierDAO;
import com.tracktive.userservice.model.DTO.SupplierDTO;
import com.tracktive.userservice.model.entity.Supplier;
import com.tracktive.userservice.model.entity.User;
import com.tracktive.userservice.repository.SupplierRepository;
import com.tracktive.userservice.util.SupplierConverter;
import com.tracktive.userservice.util.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* Description: Supplier Repository Implementation
* @author William Theo
* @date 2/3/2025
*/
@Repository
public class SupplierRepositoryImpl implements SupplierRepository {

    private final SupplierDAO supplierDAO;

    @Autowired
    SupplierRepositoryImpl(SupplierDAO supplierDAO){
        this.supplierDAO = supplierDAO;
    }

    @Override
    public List<SupplierDTO> selectAllSuppliers() {
        return Optional.ofNullable(supplierDAO.selectAllSuppliers())
                .orElse(Collections.emptyList())
                .stream()
                .map(SupplierConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SupplierDTO> selectSupplierById(String id) {
        return supplierDAO.selectSupplierById(id).map(SupplierConverter::toDTO);
    }

    @Override
    public Optional<SupplierDTO> lockSupplierById(String id) {
        return supplierDAO.lockSupplierById(id).map(SupplierConverter::toDTO);
    }

    @Override
    public boolean addSupplier(SupplierDTO supplierDTO) {
        try {
            Supplier supplier = SupplierConverter.toEntity(supplierDTO);
            return supplierDAO.addSupplier(supplier) > 0;
        } catch (DuplicateKeyException e) {
            throw new UserAlreadyExistsException("Supplier with id " + supplierDTO.getSupplierId() + " already exists", e);
        } catch (DataAccessException e) {
            throw new DatabaseOperationException("Failed to add product to the database", e);
        }
    }

    @Override
    public boolean updateSupplier(SupplierDTO supplierDTO) {
        Supplier supplier = SupplierConverter.toEntity(supplierDTO);
        return supplierDAO.updateSupplier(supplier) > 0;
    }

    @Override
    public boolean deleteById(String id) {
        return supplierDAO.deleteSupplierById(id) > 0;
    }

}
