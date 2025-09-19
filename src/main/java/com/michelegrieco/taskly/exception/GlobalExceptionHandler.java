package com.michelegrieco.taskly.exception;

import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
/** 
 * Global exception handler to manage validation errors and other exceptions across the application.
 * This class extends ResponseEntityExceptionHandler to provide custom handling for specific exceptions.
 * It currently overrides the handleMethodArgumentNotValid method to return a structured response
 * containing field-specific error messages when validation fails.
 * @param ex The exception that was thrown.
 * @param headers The HTTP headers to be included in the response.
 * @param status The HTTP status code to be returned.
 * @param request The current web request.
 * @return A ResponseEntity containing a map of field errors and a BAD_REQUEST status.
 */
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //@Override
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
}
