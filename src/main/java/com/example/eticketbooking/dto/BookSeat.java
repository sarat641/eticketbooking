package com.example.eticketbooking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private LocalDateTime showTime;
    private String date;
    @NotNull
    @NotBlank
    @Size(min = 3)
    private String movieName;
    @NotNull
    @NotBlank
    @Size(min = 3)
    private String theaterName;
    @NotNull
    @NotBlank
    @Size(min = 3)
    private String city; // City where the theater is located
    @NotEmpty
    @NotNull
    private List<String> seatNumbers; // List of seat numbers to be booked
    private int numberOfSeats; // Total number of seats to be booked
    private String seatType; // e.g., "Regular", "Premium", "VIP"
    @NotNull
    @NotBlank
    @Size(min = 3)
    private String userId; // ID of the user booking the seats
    private String paymentMethod; // Method of payment (e.g., "Credit Card", "Debit Card", "UPI")
}
