package com.example.eticketbooking.dao;


import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

/**
 * Entity class representing a Theater Master in the e-ticket booking system.
 * @author Sarat
 */
@Entity
@Table(name = "THEATERS")
@Data
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theater_id")
    private Integer theaterId;

    @Column(name = "theater_name", nullable = false)
    private String theaterName;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    private String address;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @Column(name = "is_enabled")
    private Boolean isEnabled;

    @Column(name = "is_offers_applicable")
    private Boolean isOffersApplicable;

}

