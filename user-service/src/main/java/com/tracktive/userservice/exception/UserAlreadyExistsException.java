package com.tracktive.userservice.exception;

/**
 * Description: Custom exception for user already exist
 * @author William Theo
 * @date 11/3/2025
 */
public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
