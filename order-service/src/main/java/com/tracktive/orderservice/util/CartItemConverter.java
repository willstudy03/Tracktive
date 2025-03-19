package com.tracktive.orderservice.util;

import com.tracktive.orderservice.model.DTO.CartItemDTO;
import com.tracktive.orderservice.model.entity.CartItem;

import java.util.Objects;

/**
* Description: Util for convert Order Model
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
}
