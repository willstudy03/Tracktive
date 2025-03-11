package com.tracktive.productservice.exception;

/**
* Description: Custom exception for product already exist
* @author William Theo
* @date 11/3/2025
*/
public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
