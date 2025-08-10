package com.example.eticketbooking.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Data Transfer Object for booking seats in a theater.
 * This class contains all the necessary information required to book seats for a movie show.
 *  @author Sarat
 */
@Data
public class BookSeat {
    private String showTime;
    private String date;
    private String movieName;
    private String theaterName;
    private String city; // City where the theater is located
    private List<String> seatNumbers; // List of seat numbers to be booked
    private int numberOfSeats; // Total number of seats to be booked
    private String seatType; // e.g., "Regular", "Premium", "VIP"
    private String userId; // ID of the user booking the seats
    private String showId; // Unique identifier for the show
    private String paymentMethod; // Method of payment (e.g., "Credit Card", "Debit Card", "UPI")
    private BigDecimal totalAmount; // Total amount to be charged for the booking
}
