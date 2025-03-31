package com.tracktive.productservice.model.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
* Description: Stock Management Request DTO
* @author William Theo
* @date 31/3/2025
*/
public class StockManagementRequestDTO {

    @NotNull(message = "Items list cannot be null")
    @Size(min = 1, message = "At least one product must be provided")
    @Valid
    private List<StockItemDTO> stockItems;

    public StockManagementRequestDTO() {
    }

    public StockManagementRequestDTO(List<StockItemDTO> stockItems) {
        this.stockItems = stockItems;
    }

    public List<StockItemDTO> getStockItems() {
        return stockItems;
    }

    public void setStockItems(List<StockItemDTO> stockItems) {
        this.stockItems = stockItems;
    }
}
