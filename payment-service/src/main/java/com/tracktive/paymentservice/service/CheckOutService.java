package com.tracktive.paymentservice.service;

import com.tracktive.paymentservice.model.DTO.CheckOutRequestDTO;
import com.tracktive.paymentservice.model.DTO.CheckOutResponseDTO;

/**
* Description: Payment Processor Service Interface
* @author William Theo
* @date 14/4/2025
*/
public interface CheckOutService {
    CheckOutResponseDTO initiatePayment(CheckOutRequestDTO checkOutRequestDTO);
}
