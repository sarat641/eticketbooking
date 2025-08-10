package com.example.eticketbooking.exceptions;

import lombok.Getter;

/**
 * Custom exception class for handling global exceptions in the e-ticket booking system.
 * @author Sarat
 */
@Getter
public class EticketGlobalException extends RuntimeException{
    private final Integer code;
    private final Integer httpCode;
    public EticketGlobalException(Integer code,Integer httpCode,String message) {

        super(message);
        this.code= code;
        this.httpCode = httpCode;
    }

}
