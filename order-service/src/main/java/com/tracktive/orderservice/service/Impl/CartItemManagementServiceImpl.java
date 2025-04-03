package com.tracktive.orderservice.service.Impl;

import com.tracktive.orderservice.exception.StockUnavailableException;
import com.tracktive.orderservice.grpc.StockManagementServiceGrpcClient;
import com.tracktive.orderservice.model.DTO.*;
import com.tracktive.orderservice.service.CartItemManagementService;
import com.tracktive.orderservice.service.CartItemService;
import com.tracktive.orderservice.util.PriceCalculatorUtil;
import com.tracktive.orderservice.util.converter.CartItemConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

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

        // Retrieve the cart for the given retailer
        List<CartItemDTO> cart = cartItemService.selectAllByRetailerId(cartItemManagementRequestDTO.getRetailerId());

        // Send request to Stock Management Service Server
        StockValidationResultDTO stockValidationResultDTO = stockManagementServiceGrpcClient.
                validateStock(new StockItemDTO(cartItemManagementRequestDTO.getSupplierProductId(), cartItemManagementRequestDTO.getQuantity()));

        // Extract DTO from response
        SupplierProductDTO supplierProductDTO = stockValidationResultDTO.getSupplierProductDTO();

        // Ensure that the cart contains product from the same supplier only
        if (!cart.isEmpty()){

            boolean sameSupplier = cart.stream()
                    .allMatch(cartItemDTO -> cartItemDTO.getSupplierId().equals(supplierProductDTO.getSupplierId()));

            if(!sameSupplier){
                throw new IllegalArgumentException("You can only buy products from the same supplier");
            }
        }

        if (!stockValidationResultDTO.isValid()){
            throw new StockUnavailableException("Stock Unavailable for product :" + stockValidationResultDTO
                    .getSupplierProductDTO()
                    .getSupplierProductId() + stockValidationResultDTO.getResultMessage());
        }

        // Use the utility class for price calculations
        BigDecimal finalPrice = PriceCalculatorUtil.calculateFinalPrice(
                BigDecimal.valueOf(supplierProductDTO.getPrice()),
                BigDecimal.valueOf(supplierProductDTO.getDiscountPercentage()));

        BigDecimal totalPrice = PriceCalculatorUtil.calculateTotalPrice(finalPrice, cartItemManagementRequestDTO.getQuantity());

        // Create a new CartItemRequestDTO from the validated stock details
        CartItemRequestDTO cartItemRequestDTO = new CartItemRequestDTO(
                cartItemManagementRequestDTO.getRetailerId(),
                supplierProductDTO.getSupplierProductId(),
                supplierProductDTO.getSupplierId(),
                supplierProductDTO.getProductId(),
                cartItemManagementRequestDTO.getQuantity(),
                BigDecimal.valueOf(supplierProductDTO.getPrice()), // Price Snapshot
                BigDecimal.valueOf(supplierProductDTO.getDiscountPercentage()), // Discount Percentage Snapshot
                totalPrice
        );

        CartItemManagementResponseDTO cartItemManagementResponseDTO = CartItemConverter
                .toCartItemManagementResponseDTO(cartItemService.addCartItem(cartItemRequestDTO));

        return  cartItemManagementResponseDTO;
    }
}
