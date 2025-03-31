package com.tracktive.productservice.service.Impl;

import com.tracktive.productservice.model.DTO.StockManagementRequestDTO;
import com.tracktive.productservice.model.DTO.StockManagementResponseDTO;
import com.tracktive.productservice.model.DTO.TireRequestDTO;
import com.tracktive.productservice.service.StockManagementService;
import com.tracktive.productservice.service.SupplierProductService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class StockManagementServiceImpl implements StockManagementService {

    private final SupplierProductService supplierProductService;

    private final Validator validator;

    @Autowired
    public StockManagementServiceImpl(SupplierProductService supplierProductService, Validator validator) {
        this.supplierProductService = supplierProductService;
        this.validator = validator;
    }

    @Override
    public StockManagementResponseDTO validateStock(StockManagementRequestDTO stockManagementRequestDTO) {

        validateStockManagementRequestDTO(stockManagementRequestDTO);



        return null;
    }

    @Override
    public StockManagementResponseDTO deductStock(StockManagementRequestDTO stockManagementRequestDTO) {
        return null;
    }

    private void validateStockManagementRequestDTO(StockManagementRequestDTO stockManagementRequestDTO) {
        Set<ConstraintViolation<StockManagementRequestDTO>> violations = validator.validate(stockManagementRequestDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation failed for stockManagementRequest", violations);
        }
    }
}
