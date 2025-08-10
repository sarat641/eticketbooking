package com.example.eticketbooking.dao;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * Entity class representing a Seat in the e-ticket booking system.
 * @author Sarat
 */
@Entity
@Table(
    name = "SEATS",
    uniqueConstraints = @UniqueConstraint(columnNames = {"theater_id", "seat_number"})
)
@Data
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Integer seatId;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;

    @Column(name = "seat_number", nullable = false)
    private String seatNumber;

    @Column(name = "seat_type", nullable = false)
    private String seatType;

    @Column(name = "seat_price", nullable = false)
    private BigDecimal seatPrice;

    @Column(name = "is_available")
    private Boolean isAvailable;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    // Getters and setters...
}

