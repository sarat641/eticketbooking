package com.example.eticketbooking.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Class representing the booking confirmation details.
 * This class contains all the necessary information
 * related to a booking confirmation in the e-ticket booking system.
 * @author Sarat
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BookingConfirmation {
    private String bookingId; // Unique identifier for the booking
    private String userId; // ID of the user who made the booking
    private String showId; // Unique identifier for the show
    private String theaterName; // Name of the theater
    private String movieName; // Name of the movie
    private String date; // Date of the show
    private LocalDateTime showTime; // Time of the show
    private int numberOfSeats; // Total number of seats booked
    private String seatType; // Type of seats booked (e.g., Regular, Premium, VIP)
    private List<String> seatNumbers; // List of seat numbers booked
    private String bookingStatus; // Status of the booking (e.g., Confirmed, Pending, Cancelled)
    private String bookingTimestamp; // Timestamp when the booking was made
    private String totalAmount; // Total amount charged for the booking
    private String paymentMethod; // Method of payment used for the booking
    private String confirmationMessage; // Message confirming the booking
    private String errorMessage; // Error message in case of booking failure
}
