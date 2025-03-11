package com.tracktive.productservice.exception;

/**
* Description: Custom exception for Product Not Found
* @author William Theo
* @date 11/3/2025
* @param
* @return
*/
public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String message) {
        super(message);
    }
}
