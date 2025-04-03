package com.tracktive.orderservice.util.converter;

import com.tracktive.orderservice.model.DTO.CartItemDTO;
import com.tracktive.orderservice.model.DTO.CartItemManagementResponseDTO;
import com.tracktive.orderservice.model.DTO.CartItemRequestDTO;
import com.tracktive.orderservice.model.entity.CartItem;
import org.springframework.beans.BeanUtils;

import java.util.Objects;
import java.util.UUID;

/**
* Description: Util for convert Cart Item Model
* @author William Theo
* @date 19/3/2025
*/
public class CartItemConverter {

    // Private constructor to prevent instantiation
    private CartItemConverter() {
    }

    public static CartItemDTO toDTO(CartItem cartItem) {
        if (Objects.isNull(cartItem)) {
            return null;
        }
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setId(cartItem.getId());
        cartItemDTO.setRetailerId(cartItem.getRetailerId());
        cartItemDTO.setSupplierProductId(cartItem.getSupplierProductId());
        cartItemDTO.setSupplierId(cartItem.getSupplierId());
        cartItemDTO.setProductId(cartItem.getProductId());
        cartItemDTO.setQuantity(cartItem.getQuantity());
        cartItemDTO.setPriceSnapshot(cartItem.getPriceSnapshot());
        cartItemDTO.setDiscountSnapshot(cartItem.getDiscountSnapshot());
        cartItemDTO.setSubtotal(cartItem.getSubtotal());
        cartItemDTO.setUpdatedAt(cartItem.getUpdatedAt());
        cartItemDTO.setCreatedAt(cartItem.getCreatedAt());
        return cartItemDTO;
    }

    public static CartItemDTO toDTO(CartItemRequestDTO cartItemRequestDTO){
        if (Objects.isNull(cartItemRequestDTO)) {
            return null;
        }
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setId(UUID.randomUUID().toString());
        BeanUtils.copyProperties(cartItemRequestDTO, cartItemDTO);
        return cartItemDTO;
    }

    public static CartItem toEntity(CartItemDTO cartItemDTO) {
        if (Objects.isNull(cartItemDTO)) {
            return null;
        }
        CartItem cartItem = new CartItem();
        cartItem.setId(cartItemDTO.getId());
        cartItem.setRetailerId(cartItemDTO.getRetailerId());
        cartItem.setSupplierProductId(cartItemDTO.getSupplierProductId());
        cartItem.setSupplierId(cartItemDTO.getSupplierId());
        cartItem.setProductId(cartItemDTO.getProductId());
        cartItem.setQuantity(cartItemDTO.getQuantity());
        cartItem.setPriceSnapshot(cartItemDTO.getPriceSnapshot());
        cartItem.setDiscountSnapshot(cartItemDTO.getDiscountSnapshot());
        cartItem.setSubtotal(cartItemDTO.getSubtotal());
        cartItem.setUpdatedAt(cartItemDTO.getUpdatedAt());
        cartItem.setCreatedAt(cartItemDTO.getCreatedAt());
        return cartItem;
    }

    public static CartItemManagementResponseDTO toCartItemManagementResponseDTO (CartItemDTO cartItemDTO){
        if (Objects.isNull(cartItemDTO)) {
            return null;
        }
        CartItemManagementResponseDTO cartItemManagementResponseDTO = new CartItemManagementResponseDTO();
        BeanUtils.copyProperties(cartItemDTO, cartItemManagementResponseDTO);
        return cartItemManagementResponseDTO;
    }
}
