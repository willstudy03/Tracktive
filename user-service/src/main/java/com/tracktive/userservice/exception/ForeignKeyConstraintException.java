package com.tracktive.userservice.exception;

/**
* Description: Custom Exception for ForeignKey Constraint
* @author William Theo
* @date 25/3/2025
*/
public class ForeignKeyConstraintException extends RuntimeException {
    public ForeignKeyConstraintException(String message) {
        super(message);
    }

    public ForeignKeyConstraintException(String message, Throwable cause) {
        super(message, cause);
    }
}
