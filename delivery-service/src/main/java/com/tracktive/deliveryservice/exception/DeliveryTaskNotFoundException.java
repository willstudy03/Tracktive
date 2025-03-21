package com.tracktive.deliveryservice.exception;

public class DeliveryTaskNotFoundException extends RuntimeException {
    public DeliveryTaskNotFoundException(String message) {
        super(message);
    }
}
