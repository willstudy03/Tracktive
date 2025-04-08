package com.tracktive.orderservice.util;

import com.tracktive.orderservice.model.DTO.CartItemDTO;

import java.math.BigDecimal;
import java.util.List;

/**
* Description:
* @author William Theo
* @date 3/4/2025
*/
public class PriceCalculatorUtil {
    private PriceCalculatorUtil() {
        // Private constructor to prevent instantiation
    }

    /**
     * Calculates the final price after applying a discount.
     *
     * @param price             The original price of the product.
     * @param discountPercentage The discount percentage to apply.
     * @return The final price after discount.
     */
    public static BigDecimal calculateFinalPrice(BigDecimal price, BigDecimal discountPercentage) {
        if (discountPercentage == null || discountPercentage.compareTo(BigDecimal.ZERO) <= 0) {
            return price; // No discount, return original price
        }
        BigDecimal discountAmount = price.multiply(discountPercentage).divide(BigDecimal.valueOf(100));
        return price.subtract(discountAmount);
    }

    /**
     * Calculates the total price for a given quantity of the product.
     *
     * @param finalPrice The price per unit after discount.
     * @param quantity   The number of units.
     * @return The total price for the given quantity.
     */
    public static BigDecimal calculateTotalPrice(BigDecimal finalPrice, int quantity) {
        return finalPrice.multiply(BigDecimal.valueOf(quantity));
    }

    /**
     * Calculates the total price of all cart items by summing their subtotals.
     *
     * @param cartItems List of CartItemDTO, each with its own subtotal.
     * @return Total price for the entire cart.
     */
    public static BigDecimal calculateTotalCartPrice(List<CartItemDTO> cartItems) {
        if (cartItems == null || cartItems.isEmpty()) {
            return BigDecimal.ZERO;
        }

        return cartItems.stream()
                .map(CartItemDTO::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
