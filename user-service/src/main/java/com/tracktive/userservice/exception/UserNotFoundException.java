package com.tracktive.userservice.exception;

/**
* Description: Custom exception for user not found
* @author William Theo
* @date 5/3/2025
*/
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
