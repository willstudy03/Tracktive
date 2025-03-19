package com.tracktive.orderservice.exception;

public class CartItemAlreadyExistException extends RuntimeException {
    public CartItemAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
