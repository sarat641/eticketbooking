package com.example.eticketbooking.dao;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;
import java.time.LocalDate;

/**
 * Entity class representing a Movie shows master in the e-ticket booking system.
 * @author Sarat
 */
@Entity
@Table(name = "SHOWS")
@Data
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "show_id")
    private Integer showId;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;

    @Column(name = "show_time", nullable = false)
    private OffsetDateTime showTime;

    @Column(name = "show_date", nullable = false)
    private LocalDate showDate;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
}

