package com.example.eticketbooking.dao;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entity class representing a Movie Genre in the e-ticket booking system.
 * @author Sarat
 */
@Entity
@Table(name = "MOVIE_GENRES")
@Data
public class MovieGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Integer genreId;

    @Column(name = "genre_name", nullable = false, unique = true)
    private String genreName;

}

