package com.tracktive.productservice.exception;
/**
* Description: Custom Exception for Kafka producer failed to send event
* @author William Theo
* @date 10/4/2025
*/
public class FailedToSendEventException extends RuntimeException {

    public FailedToSendEventException(String message) {
        super(message);
    }

    public FailedToSendEventException(String message, Throwable cause) {
        super(message, cause);
    }
}
