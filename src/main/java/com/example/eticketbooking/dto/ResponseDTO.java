package com.example.eticketbooking.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Response Data Transfer Object (DTO) for encapsulating API responses.
 * This class contains an error code, an error message, and a data object.
 * It is used to standardize the structure of responses returned by the API.
 *  @param <T> The type of data contained in the response.
 *  @author Sarat
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
public class ResponseDTO<T> {
    private Integer errorCode; // Error code for the response
    private String errorMessage; // Error message for the response
    private DataDTO<T> data; // Data object containing the response data
}
