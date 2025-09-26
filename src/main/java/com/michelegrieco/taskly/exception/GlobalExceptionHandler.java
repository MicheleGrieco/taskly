package com.michelegrieco.taskly.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
/**
 * Global exception handler to manage and respond to exceptions across the application.
 * Handles specific exceptions like TaskNotFoundException and validation errors,
 * as well as a generic handler for all other exceptions.
 * Extends ResponseEntityExceptionHandler to leverage Spring's built-in exception handling capabilities.
 * @see ResponseEntityExceptionHandler
 * @see TaskNotFoundException
 * @see MethodArgumentNotValidException
 */
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    /**
     * Handle validation exceptions and return a structured error response.
     * @param ex The MethodArgumentNotValidException instance.
     * @return A ResponseEntity containing a map of field errors and a BAD_REQUEST status.
     */
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    /**
     * Handle TaskNotFoundException and return a NOT_FOUND response.
     * @param ex The TaskNotFoundException instance.
     * @return A ResponseEntity containing the exception message and a NOT_FOUND status.
     */
    public ResponseEntity<String> handleTaskNotFoundException(TaskNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    /**
     * Handle all other exceptions not specifically handled elsewhere.
     * @param ex The exception that was thrown.
     * @return A ResponseEntity containing the exception message and an INTERNAL_SERVER_ERROR status.
     */
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    /**
     * Handle UserNotFoundException and return a NOT_FOUND response.
     * @param ex The UserNotFoundException instance.
     * @return A ResponseEntity containing the exception message and a NOT_FOUND status.
     */
    public ResponseEntity<String> handleUserNotFoundExcception(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
