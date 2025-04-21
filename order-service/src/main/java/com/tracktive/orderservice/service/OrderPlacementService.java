package com.tracktive.orderservice.service;

import com.tracktive.orderservice.model.DTO.OrderPlacementRequestDTO;
import com.tracktive.orderservice.model.DTO.OrderPlacementResponseDTO;

/**
* Description: Order Management Service Interface
* @author William Theo
* @date 4/4/2025
*/
public interface OrderPlacementService {
    OrderPlacementResponseDTO placeOrder(OrderPlacementRequestDTO orderPlacementRequestDTO);
}
