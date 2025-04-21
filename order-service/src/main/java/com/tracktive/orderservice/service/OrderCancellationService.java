package com.tracktive.orderservice.service;

import com.tracktive.orderservice.model.DTO.OrderCancellationRequestDTO;
import com.tracktive.orderservice.model.DTO.OrderCancellationResponseDTO;

/**
* Description: Order Cancellation Service Interface
* @author William Theo
* @date 21/4/2025
*/
public interface OrderCancellationService {
    OrderCancellationResponseDTO cancelOrder(OrderCancellationRequestDTO orderCancellationRequestDTO);
}
