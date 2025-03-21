package com.tracktive.deliveryservice.exception;

/**
 * Description: Custom exception for database operation
 * @author William Theo
 * @date 21/3/2025
 */
public class DatabaseOperationException extends RuntimeException{

    public DatabaseOperationException(String message) {
        super(message);
    }

    public DatabaseOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
