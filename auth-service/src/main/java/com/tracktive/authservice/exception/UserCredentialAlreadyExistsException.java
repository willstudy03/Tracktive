package com.tracktive.authservice.exception;

public class UserCredentialAlreadyExistsException extends RuntimeException {
    public UserCredentialAlreadyExistsException(String message) {
        super(message);
    }

    public UserCredentialAlreadyExistsException(String message, Throwable cause) {
      super(message, cause);
    }
}
