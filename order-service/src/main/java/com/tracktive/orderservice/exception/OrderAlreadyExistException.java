package com.tracktive.orderservice.exception;


/**
* Description: Custom exception for order already exist
* @author William Theo
* @date 16/3/2025
*/
public class OrderAlreadyExistException extends RuntimeException{
    public OrderAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
