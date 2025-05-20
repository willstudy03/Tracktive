package com.tracktive.authservice.util.handler;

import com.tracktive.authservice.exception.InvalidUserCredentialException;
import com.tracktive.authservice.exception.LockAcquisitionException;
import com.tracktive.authservice.exception.UserCredentialAlreadyExistsException;
import com.tracktive.authservice.exception.UserCredentialNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

/**
* Description: Global Exception Handler
* @author William Theo
* @date 27/3/2025
*/
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException exception){

        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult().getFieldErrors().
                forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException exception) {
        Map<String, String> errors = new HashMap<>();

        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            errors.put(violation.getPropertyPath().toString(), violation.getMessage());
        }

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> handleSQLIntegrityConstraintViolation(SQLIntegrityConstraintViolationException ex) {
        // Extract message and return a proper response
        String message = "Duplicate entry error: " + ex.getMessage();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

    @ExceptionHandler(LockAcquisitionException.class)
    public ResponseEntity<String> handleLockAcquisitionException(LockAcquisitionException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UserCredentialAlreadyExistsException.class)
    public ResponseEntity<String> handleUserCredentialAlreadyExistsException(UserCredentialAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UserCredentialNotFoundException.class)
    public ResponseEntity<String> handleUserCredentialNotFoundException(UserCredentialNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidUserCredentialException.class)
    public ResponseEntity<String> handleInvalidUserCredentialException(InvalidUserCredentialException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
