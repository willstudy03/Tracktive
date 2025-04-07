package com.tracktive.orderservice.service;

import com.tracktive.orderservice.model.DTO.OrderActionRequestDTO;
import com.tracktive.orderservice.model.DTO.OrderActionResponseDTO;

/**
* Description: Order Management Service Interface
* @author William Theo
* @date 4/4/2025
*/
public interface OrderActionService {
    OrderActionResponseDTO placeOrder(OrderActionRequestDTO orderActionRequestDTO);
}
