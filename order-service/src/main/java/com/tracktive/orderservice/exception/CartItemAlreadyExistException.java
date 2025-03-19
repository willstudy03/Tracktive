package com.tracktive.orderservice.exception;

/**
* Description: Custom exception for cart item already exist
* @author William Theo
* @date 19/3/2025
*/
public class CartItemAlreadyExistException extends RuntimeException {
    public CartItemAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
