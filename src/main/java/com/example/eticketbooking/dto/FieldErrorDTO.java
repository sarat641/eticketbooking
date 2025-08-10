package com.example.eticketbooking.dto;

import lombok.Data;

/**
 * DTO for field error details.
 * This class is used to encapsulate the field name and error message for validation errors.
 * @author Sarat
 */
@Data
public class FieldErrorDTO {
    private final String field;
    private final String message;
}
