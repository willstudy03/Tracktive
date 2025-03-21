package com.tracktive.deliveryservice.exception;

/**
* Description: Custom Exception for Delivery Task Already Exist
* @author William Theo
* @date 21/3/2025
*/
public class DeliveryTaskAlreadyExistException extends RuntimeException {

    public DeliveryTaskAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
