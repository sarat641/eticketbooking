package com.example.eticketbooking.dao;

import java.io.Serializable;
import java.util.Objects;

/**
 * Composite key class for the MovieGenreRelation entity.
 * @author Sarat
 */
public class MovieGenreRelationId implements Serializable {
    private Integer movieId;
    private Integer genreId;

    public MovieGenreRelationId() {}

    public MovieGenreRelationId(Integer movieId, Integer genreId) {
        this.movieId = movieId;
        this.genreId = genreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieGenreRelationId)) return false;
        MovieGenreRelationId that = (MovieGenreRelationId) o;
        return Objects.equals(movieId, that.movieId) && Objects.equals(genreId, that.genreId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, genreId);
    }
}

