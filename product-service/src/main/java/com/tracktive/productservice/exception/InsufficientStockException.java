package com.tracktive.productservice.exception;

/**
* Description: Custom Exception for Insufficient Stock
* @author William Theo
* @date 1/4/2025
*/
public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String message) {
        super(message);
    }

    public InsufficientStockException(String message, Throwable cause) {
        super(message, cause);
    }
}
