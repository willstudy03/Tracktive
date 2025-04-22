package com.tracktive.userservice.exception;
/**
* Description: Custom exception for Duplicate Email
* @author William Theo
* @date 22/4/2025
*/
public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String message) {
        super(message);
    }
}
