package com.tracktive.userservice.repository.Impl;

import com.tracktive.userservice.model.DAO.SupplierDAO;
import com.tracktive.userservice.model.DTO.SupplierDTO;
import com.tracktive.userservice.model.entity.Supplier;
import com.tracktive.userservice.repository.SupplierRepository;
import com.tracktive.userservice.util.SupplierConverter;
import org.springframework.beans.factory.annotation.Autowired;
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
        validateId(id);
        return supplierDAO.selectSupplierById(id).map(SupplierConverter::toDTO);
    }

    @Override
    public Optional<SupplierDTO> lockSupplierById(String id) {
        validateId(id);
        return supplierDAO.lockSupplierById(id).map(SupplierConverter::toDTO);
    }

    @Override
    public boolean addSupplier(SupplierDTO supplierDTO) {
        validateSupplierDTO(supplierDTO);
        Supplier supplier = SupplierConverter.toEntity(supplierDTO);
        return supplierDAO.addSupplier(supplier) > 0;
    }

    @Override
    public boolean updateSupplier(SupplierDTO supplierDTO) {
        validateSupplierDTO(supplierDTO);
        Supplier supplier = SupplierConverter.toEntity(supplierDTO);
        return supplierDAO.updateSupplier(supplier) > 0;
    }

    @Override
    public boolean deleteById(String id) {
        validateId(id);
        return supplierDAO.deleteSupplierById(id) > 0;
    }

    private void validateId(String id){
        if (Objects.isNull(id) || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
    }

    private void validateSupplierDTO(SupplierDTO supplierDTO) {
        if (Objects.isNull(supplierDTO)) {
            throw new IllegalArgumentException("SupplierDTO cannot be null");
        }
    }
}
