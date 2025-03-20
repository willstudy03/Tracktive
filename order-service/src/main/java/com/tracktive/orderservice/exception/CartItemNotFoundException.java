package com.tracktive.orderservice.exception;

/**
* Description: Custom Exception for Cart Item not found
* @author William Theo
* @date 20/3/2025
*/
public class CartItemNotFoundException extends RuntimeException{
    public CartItemNotFoundException(String message) {
        super(message);
    }
}
