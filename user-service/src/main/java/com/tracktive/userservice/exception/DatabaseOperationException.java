package com.tracktive.userservice.exception;

/**
 * Description: Description: Custom exception for database operation
 * @author William Theo
 * @date 11/3/2025
 */
public class DatabaseOperationException extends RuntimeException{

    public DatabaseOperationException(String message) {
        super(message);
    }

    public DatabaseOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}