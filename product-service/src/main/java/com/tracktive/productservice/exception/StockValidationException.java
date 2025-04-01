package com.tracktive.productservice.exception;

import com.tracktive.productservice.model.DTO.StockValidationResultDTO;

import java.util.List;
/**
* Description: Custom Exception for Stock Validation
* @author William Theo
* @date 1/4/2025
*/
public class StockValidationException extends RuntimeException {

    private final List<StockValidationResultDTO> validationResults;

    public StockValidationException(String message, List<StockValidationResultDTO> validationResults) {
        super(message);
        this.validationResults = validationResults;
    }

    public List<StockValidationResultDTO> getValidationResults() {
        return validationResults;
    }

}
