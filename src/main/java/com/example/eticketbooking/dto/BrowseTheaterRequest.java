package com.example.eticketbooking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

/**
 * Request DTO for browsing theaters based on movie name, city, and date.
 * @author Sarat
 */
@Data
public class BrowseTheaterRequest {
    @NotNull
    @NotBlank
    @Size(min = 3)
    @Schema(defaultValue = "Bahubali")
    private String movieName;
    @NotNull
    @NotBlank
    @Size(min = 3)
    @Schema(defaultValue = "Bangalore")
    private String city;
    @NotNull
    @FutureOrPresent
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Schema(defaultValue = "18-08-2025")
    private LocalDate date;
}
