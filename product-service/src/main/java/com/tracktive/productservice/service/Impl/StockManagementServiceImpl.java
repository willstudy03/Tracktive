package com.tracktive.productservice.service.Impl;

import com.tracktive.productservice.exception.ProductNotFoundException;
import com.tracktive.productservice.exception.StockValidationException;
import com.tracktive.productservice.model.DTO.*;
import com.tracktive.productservice.service.StockManagementService;
import com.tracktive.productservice.service.SupplierProductService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StockManagementServiceImpl implements StockManagementService {

    private final SupplierProductService supplierProductService;

    private static final Logger logger = LoggerFactory.getLogger(StockManagementServiceImpl.class);

    private final Validator validator;

    @Autowired
    public StockManagementServiceImpl(SupplierProductService supplierProductService, Validator validator) {
        this.supplierProductService = supplierProductService;
        this.validator = validator;
    }

    @Override
    public StockManagementResponseDTO validateStock(StockManagementRequestDTO stockManagementRequestDTO) {

        validateStockManagementRequestDTO(stockManagementRequestDTO);

        List<StockValidationResultDTO> validationResults = stockManagementRequestDTO.getStockItems()
                .stream()
                .map(stockItemDTO -> {
                    SupplierProductDTO supplierProductDTO = supplierProductService.selectSupplierProductById(stockItemDTO.getSupplierProductID());
                    boolean isAvailable = supplierProductDTO.getStockQuantity() >= stockItemDTO.getQuantity();
                    return new StockValidationResultDTO(supplierProductDTO, isAvailable, isAvailable ? "Stock available" : "Insufficient stock");
                })
                .collect(Collectors.toList());

        return new StockManagementResponseDTO(validationResults);
    }

    @Override
    @Transactional
    public StockManagementResponseDTO deductStock(StockManagementRequestDTO stockManagementRequestDTO) {

        validateStockManagementRequestDTO(stockManagementRequestDTO);

        // Step 1: Lock all products and validate the stock availability
        List<StockValidationResultDTO> validationResults = stockManagementRequestDTO.getStockItems()
                .stream()
                .map(stockItemDTO -> {
                    SupplierProductDTO supplierProductDTO = supplierProductService.lockSupplierProductById(stockItemDTO.getSupplierProductID());
                    boolean isAvailable = supplierProductDTO.getStockQuantity() >= stockItemDTO.getQuantity();
                    return new StockValidationResultDTO(supplierProductDTO, isAvailable, isAvailable ? "Stock available" : "Insufficient stock");
                })
                .collect(Collectors.toList());

        // Step 2: If any validation failed, rollback transaction
        boolean allValid = validationResults.stream().allMatch(StockValidationResultDTO::isValid);
        if (!allValid) {
            throw new StockValidationException("Stock validation failed", validationResults);
        }

        // Step 3: Deduct stock for all products
        List<StockValidationResultDTO> deductionResults = new ArrayList<>();

        for (StockItemDTO stockItemDTO : stockManagementRequestDTO.getStockItems()) {
            SupplierProductDTO updatedProduct = supplierProductService.deductSupplierProductStock(
                    stockItemDTO.getSupplierProductID(),
                    stockItemDTO.getQuantity()
            );

            deductionResults.add(new StockValidationResultDTO(
                    updatedProduct,
                    true,
                    "Stock deducted successfully. Remaining: " + updatedProduct.getStockQuantity()
            ));
        }

        // Step 4: Create and return response with deduction results
        return new StockManagementResponseDTO(deductionResults);
    }

    @Override
    @Transactional
    public StockManagementResponseDTO addStock(StockManagementRequestDTO stockManagementRequestDTO) {

        validateStockManagementRequestDTO(stockManagementRequestDTO);

        List<StockValidationResultDTO> results = new ArrayList<>();

        for(StockItemDTO stockItemDTO: stockManagementRequestDTO.getStockItems()){

            try{
                SupplierProductDTO supplierProductDTO = supplierProductService.lockSupplierProductById(stockItemDTO.getSupplierProductID());

                supplierProductDTO.setStockQuantity(supplierProductDTO.getStockQuantity() + stockItemDTO.getQuantity());

                SupplierProductDTO updatedProduct = supplierProductService.updateSupplierProduct(supplierProductDTO);

                results.add(new StockValidationResultDTO(
                        updatedProduct,
                        true,
                        "Stock added successfully. New quantity: " + updatedProduct.getStockQuantity()
                ));

            } catch (ProductNotFoundException e) {
                // Supplier might have deleted the product
                results.add(new StockValidationResultDTO(
                        null,
                        false,
                        "Product ID " + stockItemDTO.getSupplierProductID() + " not found. It may have been removed."
                ));

                logger.warn("Stock add failed: SupplierProduct ID {} not found", stockItemDTO.getSupplierProductID());

            }
        }

        return new  StockManagementResponseDTO(results);
    }

    private void validateStockManagementRequestDTO(StockManagementRequestDTO stockManagementRequestDTO) {
        Set<ConstraintViolation<StockManagementRequestDTO>> violations = validator.validate(stockManagementRequestDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation failed for stockManagementRequest", violations);
        }
    }
}
