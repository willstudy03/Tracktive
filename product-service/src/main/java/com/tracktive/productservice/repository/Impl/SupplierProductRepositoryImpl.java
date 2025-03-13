package com.tracktive.productservice.repository.Impl;

import com.tracktive.productservice.exception.DatabaseOperationException;
import com.tracktive.productservice.exception.ProductAlreadyExistsException;
import com.tracktive.productservice.model.DAO.SupplierProductDAO;
import com.tracktive.productservice.model.DTO.SupplierProductDTO;
import com.tracktive.productservice.model.entity.SupplierProduct;
import com.tracktive.productservice.repository.SupplierProductRepository;
import com.tracktive.productservice.util.SupplierProductConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
* Description: Supplier Product Repository Implementation
* @author William Theo
* @date 10/3/2025
*/
@Repository
public class SupplierProductRepositoryImpl implements SupplierProductRepository {

    private final SupplierProductDAO supplierProductDAO;

    @Autowired
    public SupplierProductRepositoryImpl(SupplierProductDAO supplierProductDAO) {
        this.supplierProductDAO = supplierProductDAO;
    }

    @Override
    public List<SupplierProductDTO> selectAllSupplierProducts() {
        return supplierProductDAO.selectAllSupplierProducts()
                .stream()
                .map(SupplierProductConverter::toDTO)
                .toList();
    }

    @Override
    public List<SupplierProductDTO> selectSupplierProductsBySupplierId(String supplierId) {
        return supplierProductDAO.selectSupplierProductsBySupplierId(supplierId)
                .stream()
                .map(SupplierProductConverter::toDTO)
                .toList();
    }

    @Override
    public Optional<SupplierProductDTO> selectSupplierProductById(String id) {
        return supplierProductDAO.selectSupplierProductById(id).map(SupplierProductConverter::toDTO);
    }

    @Override
    public Optional<SupplierProductDTO> lockSupplierProductById(String id) {
        return supplierProductDAO.lockSupplierProductById(id).map(SupplierProductConverter::toDTO);
    }

    @Override
    public boolean addSupplierProduct(SupplierProductDTO supplierProductDTO) {
        try {
            SupplierProduct supplierProduct = SupplierProductConverter.toEntity(supplierProductDTO);
            return supplierProductDAO.addSupplierProduct(supplierProduct) > 0;
        } catch (DuplicateKeyException e) {
            throw new ProductAlreadyExistsException("Supplier product with id " + supplierProductDTO.getSupplierProductId() + " already exists", e);
        } catch (DataAccessException e) {
            throw new DatabaseOperationException("Failed to add supplier product to the database", e);
        }
    }

    @Override
    public boolean updateSupplierProduct(SupplierProductDTO supplierProductDTO) {
        SupplierProduct supplierProduct = SupplierProductConverter.toEntity(supplierProductDTO);
        return supplierProductDAO.updateSupplierProduct(supplierProduct) > 0;
    }

    @Override
    public boolean deleteSupplierProductById(String id) {
        return supplierProductDAO.deleteSupplierProductById(id) > 0;
    }
}
