package com.example.eticketbooking.dao;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * Entity class representing a Ticket Booking in the e-ticket booking system.
 * @author Sarat
 */
@Entity
@Table(
    name = "TICKET_BOOKINGS",
    uniqueConstraints = @UniqueConstraint(columnNames = {"booking_id", "seat_id"})
)
@Data
public class TicketBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Integer ticketId;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private UserBooking booking;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @Column(name = "ticket_price", nullable = false)
    private BigDecimal ticketPrice;

    @Column(name = "booking_time")
    private OffsetDateTime bookingTime;

    @Column(name = "is_refunded")
    private Boolean isRefunded;

}

