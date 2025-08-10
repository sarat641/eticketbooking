package com.example.eticketbooking.dao;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * Entity class representing a User Booking in the e-ticket booking system.
 * @author Sarat
 */
@Entity
@Table(name = "USER_BOOKINGS")
@Data
public class UserBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Integer bookingId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;

    @Column(name = "booking_time")
    private OffsetDateTime bookingTime;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

    @Column(name = "payment_status", nullable = false)
    private String paymentStatus;

    @Column(name = "is_cancelled")
    private Boolean isCancelled;

}

