package com.example.eticketbooking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Data Transfer Object for booking seats in a theater.
 * This class contains all the necessary information required to book seats for a movie show.
 *
 * @author Sarat
 */
@Data
public class BookSeat {
    @NotNull
    @FutureOrPresent
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @Schema(defaultValue = "10-08-2025 22:00:00")
    private LocalDateTime showTime;
    private String date;
    @NotNull
    @NotBlank
    @Size(min = 3)
    @Schema(defaultValue = "Bahubali")
    private String movieName;
    @NotNull
    @NotBlank
    @Size(min = 3)
    @Schema(defaultValue = "PVR Cinemas")
    private String theaterName;
    @NotNull
    @NotBlank
    @Size(min = 3)
    @Schema(defaultValue = "Bangalore")
    private String city; // City where the theater is located
    @NotEmpty
    @NotNull
    @Schema(description = "List of seat numbers to be booked", example = "[\"A1\", \"A2\"]")
    private List<String> seatNumbers; // List of seat numbers to be booked
    private int numberOfSeats; // Total number of seats to be booked
    private String seatType; // e.g., "Regular", "Premium", "VIP"
    @NotNull
    @NotBlank
    @Size(min = 3)
    @Schema(defaultValue = "sarat@gmail.com")
    private String userId; // ID of the user booking the seats
    private String paymentMethod; // Method of payment (e.g., "Credit Card", "Debit Card", "UPI")
}
