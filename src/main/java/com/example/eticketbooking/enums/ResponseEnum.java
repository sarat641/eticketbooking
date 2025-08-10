package com.example.eticketbooking.enums;

/**
 * Enum representing various response statuses for the e-ticket booking application.
 * Each enum constant has a unique code, an HTTP status code, and a message.
 *  @author Sarat
 */
public enum ResponseEnum {
    SUCCESS(1000,200,"Success"),
    FAILURE(1001,400,"Failure"),
    INVALID_REQUEST(1002,400,"Invalid Request"),
    NOT_FOUND(1003,400,"Not Found"),
    SERVER_ERROR(1004,500,"Server Error"),
    NO_SEATS_AVAILABLE(1005,400,"No seats available for the selected show.");

    private final Integer code;
    private final String message;
    private final Integer httpCode;


    ResponseEnum(Integer code,Integer httpCode ,String message) {
        this.message = message;
        this.code = code;
        this.httpCode = httpCode;
    }

    public String getMessage() {
        return message;
    }
    public Integer getCode() {
        return code;
    }
    public Integer getHttpCode() {
        return httpCode;
    }
}
