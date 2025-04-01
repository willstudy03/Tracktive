package com.tracktive.productservice.model.DTO;

import java.util.List;

/**
* Description: Stock Management Response DTO
* @author William Theo
* @date 31/3/2025
*/
public class StockManagementResponseDTO {

    private List<StockValidationResultDTO> results;

    public StockManagementResponseDTO() {
    }

    public StockManagementResponseDTO(List<StockValidationResultDTO> results) {
        this.results = results;
    }

    public List<StockValidationResultDTO> getResults() {
        return results;
    }

    public void setResults(List<StockValidationResultDTO> results) {
        this.results = results;
    }
}
