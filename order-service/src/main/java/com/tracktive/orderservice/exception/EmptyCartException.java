package com.tracktive.orderservice.exception;

/**
* Description: Custom Exception for Empty Cart during Order Placement
* @author William Theo
* @date 8/4/2025
*/
public class EmptyCartException extends RuntimeException {
    public EmptyCartException(String message) {
        super(message);
    }
}
