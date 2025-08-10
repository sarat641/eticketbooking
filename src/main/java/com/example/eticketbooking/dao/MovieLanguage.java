package com.example.eticketbooking.dao;

import jakarta.persistence.*;
import lombok.Data;
/**
 * Entity class representing a Movie Language Master in the e-ticket booking system.
 * @author Sarat
 */

@Entity
@Table(name = "MOVIE_LANGUAGES")
@Data
public class MovieLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Integer languageId;

    @Column(name = "language_name", nullable = false, unique = true)
    private String languageName;
}


