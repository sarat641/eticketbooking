package com.example.eticketbooking.exceptions;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Global exception handler for the e-ticket booking application.
 * This class will handle exceptions thrown by the application.
 * @author Sarat
 */

@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String invalidJson() {
        return """
               Invalid JSON format: 
               Please ensure that the JSON is properly formatted and all required fields are included.
               """;
    }
}
