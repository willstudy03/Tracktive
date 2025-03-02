package com.tracktive.userservice.repository.Impl;

import com.tracktive.userservice.model.DAO.SupplierDAO;
import com.tracktive.userservice.model.entity.Supplier;
import com.tracktive.userservice.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    public List<Supplier> selectAllSuppliers() {
        return Optional.ofNullable(supplierDAO.selectAllSuppliers()).orElse(Collections.emptyList());
    }

    @Override
    public Optional<Supplier> selectSupplierById(String id) {
        return supplierDAO.selectSupplierById(id);
    }

    @Override
    public Optional<Supplier> lockUserById(String id) {
        return supplierDAO.lockSupplierById(id);
    }

    @Override
    public boolean addSupplier(Supplier supplier) {
        return supplierDAO.addSupplier(supplier) > 0;
    }

    @Override
    public boolean updateSupplier(Supplier supplier) {
        return supplierDAO.updateSupplier(supplier) > 0;
    }

    @Override
    public boolean deleteById(String id) {
        return supplierDAO.deleteSupplierById(id) > 0;
    }
}
