package com.example.eticketbooking.dao;


import jakarta.persistence.*;
import lombok.Data;

/**
 * Entity class representing the relationship between movies and genres in the e-ticket booking system.
 * @author Sarat
 */
@Entity
@Table(name = "MOVIE_GENRE_RELATION")
@IdClass(MovieGenreRelationId.class)
@Data
public class MovieGenreRelation {
    @Id
    @Column(name = "movie_id")
    private Integer movieId;

    @Id
    @Column(name = "genre_id")
    private Integer genreId;
}

