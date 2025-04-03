package com.tracktive.orderservice.exception;
/**
* Description: Custom Exception for Stock Unavailable
* @author William Theo
* @date 3/4/2025
*/
public class StockUnavailableException extends RuntimeException {
    public StockUnavailableException(String message) {
        super(message);
    }
}
