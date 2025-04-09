package com.tracktive.productservice.service;


import com.tracktive.productservice.model.DTO.StockManagementRequestDTO;
import com.tracktive.productservice.model.DTO.StockManagementResponseDTO;

/**
* Description: Stock Management Service
* @author William Theo
* @date 31/3/2025
*/
public interface StockManagementService {
    StockManagementResponseDTO validateStock(StockManagementRequestDTO stockManagementRequestDTO);
    StockManagementResponseDTO deductStock(StockManagementRequestDTO stockManagementRequestDTO);
    StockManagementResponseDTO addStock(StockManagementRequestDTO stockManagementRequestDTO);
}
