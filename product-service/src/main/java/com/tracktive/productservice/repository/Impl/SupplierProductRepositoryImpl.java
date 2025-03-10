package com.tracktive.productservice.repository.Impl;

import com.tracktive.productservice.model.DAO.SupplierProductDAO;
import com.tracktive.productservice.model.DTO.SupplierProductDTO;
import com.tracktive.productservice.model.entity.SupplierProduct;
import com.tracktive.productservice.repository.SupplierProductRepository;
import com.tracktive.productservice.util.SupplierProductConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
        validateSupplierId(supplierId);
        return supplierProductDAO.selectSupplierProductsBySupplierId(supplierId)
                .stream()
                .map(SupplierProductConverter::toDTO)
                .toList();
    }

    @Override
    public Optional<SupplierProductDTO> selectSupplierProductById(String id) {
        validateId(id);
        return supplierProductDAO.selectSupplierProductById(id).map(SupplierProductConverter::toDTO);
    }

    @Override
    public Optional<SupplierProductDTO> lockSupplierProductById(String id) {
        validateId(id);
        return supplierProductDAO.lockSupplierProductById(id).map(SupplierProductConverter::toDTO);
    }

    @Override
    public boolean addSupplierProduct(SupplierProductDTO supplierProductDTO) {
        validateSupplierProductDTO(supplierProductDTO);
        SupplierProduct supplierProduct = SupplierProductConverter.toEntity(supplierProductDTO);
        return supplierProductDAO.addSupplierProduct(supplierProduct) > 0;
    }

    @Override
    public boolean updateSupplierProduct(SupplierProductDTO supplierProductDTO) {
        validateSupplierProductDTO(supplierProductDTO);
        SupplierProduct supplierProduct = SupplierProductConverter.toEntity(supplierProductDTO);
        return supplierProductDAO.updateSupplierProduct(supplierProduct) > 0;
    }

    @Override
    public boolean deleteSupplierProductById(String id) {
        validateId(id);
        return supplierProductDAO.deleteSupplierProductById(id) > 0;
    }

    private void validateId(String id){
        if (Objects.isNull(id) || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Supplier Product ID cannot be null or empty");
        }
    }

    private void validateSupplierId(String supplierId){
        if (Objects.isNull(supplierId) || supplierId.trim().isEmpty()) {
            throw new IllegalArgumentException("Supplier Id cannot be null or empty");
        }
    }

    private void validateSupplierProductDTO(SupplierProductDTO supplierProductDTO) {
        if (Objects.isNull(supplierProductDTO)) {
            throw new IllegalArgumentException("SupplierProductDTO cannot be null");
        }
    }
}
