package com.example.eticketbooking.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO for validation errors.
 * This class is used to encapsulate validation errors that occur during request processing.
 * It contains a list of field errors, each represented by a FieldErrorDTO.
 *
 * @author Sarat
 */
public class ValidationErrorDTO {
    private final List<FieldErrorDTO> fieldErrors = new ArrayList<>();

    public ValidationErrorDTO() {

    }

    public void addFieldError(String path, String message) {
        FieldErrorDTO error = new FieldErrorDTO(path, message);
        fieldErrors.add(error);
    }

    public List<FieldErrorDTO> getFieldErrors() {
        return fieldErrors;
    }
}
