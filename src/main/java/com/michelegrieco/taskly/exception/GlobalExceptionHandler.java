package com.michelegrieco.taskly.exception;

import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    /** 
     * Handle validation errors and return a structured error response.
     * @param ex The exception that was thrown.
     * @param headers The HTTP headers to be included in the response.
     * @param status The HTTP status code to be returned.
     * @param request The current web request.
     * @return A ResponseEntity containing a map of field errors and a BAD_REQUEST status.
     */
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                    HttpHeaders headers,
                                                                    HttpStatus status,
                                                                    WebRequest request) {
        
        // Create a map to hold field-specific error messages                                                         
        Map<String, String> errors = new HashMap<>();
        // Populate the map with field names and their corresponding error messages
        ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage()));
            
        // Return a ResponseEntity with the errors map and a BAD_REQUEST status
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

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
}
