package com.tracktive.orderservice.service.Impl;

import com.tracktive.orderservice.grpc.StockManagementServiceGrpcClient;
import com.tracktive.orderservice.model.DTO.CartItemManagementRequestDTO;
import com.tracktive.orderservice.model.DTO.CartItemManagementResponseDTO;
import com.tracktive.orderservice.service.CartItemManagementService;
import com.tracktive.orderservice.service.CartItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* Description: Cart Item Management Service Implementation
* @author William Theo
* @date 2/4/2025
*/
@Service
public class CartItemManagementServiceImpl implements CartItemManagementService {

    private final CartItemService cartItemService;

    private final StockManagementServiceGrpcClient stockManagementServiceGrpcClient;

    private static final Logger log = LoggerFactory.getLogger(CartItemManagementServiceImpl.class);

    @Autowired
    public CartItemManagementServiceImpl(CartItemService cartItemService, StockManagementServiceGrpcClient stockManagementServiceGrpcClient) {
        this.cartItemService = cartItemService;
        this.stockManagementServiceGrpcClient = stockManagementServiceGrpcClient;
    }

    @Override
    @Transactional
    public CartItemManagementResponseDTO addProductToCart(CartItemManagementRequestDTO cartItemManagementRequestDTO) {
        return null;
    }
}
