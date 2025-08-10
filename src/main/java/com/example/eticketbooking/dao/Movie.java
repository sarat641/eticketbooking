package com.example.eticketbooking.dao;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

/**
 *  Entity class representing a Movie Master in the e-ticket booking system.
 *  @author Sarat
 */
@Entity
@Table(name = "MOVIES")
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Integer movieId;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(columnDefinition = "jsonb")
    private String metadata;

    @Column(columnDefinition = "jsonb")
    private String criteria;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private MovieLanguage language;

    @Column(name = "created_at")
    private java.time.OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private java.time.OffsetDateTime updatedAt;

}

