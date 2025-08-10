package com.example.eticketbooking.dao;


import jakarta.persistence.*;
import lombok.Data;

/**
 * Entity class representing a City Master in the e-ticket booking system.
 * @author Sarat
 */
@Entity
@Table(name = "CITIES")
@Data
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "city_name", nullable = false)
    private String cityName;

    @Column(name = "is_offers_applicable")
    private Boolean isOffersApplicable;

    @ManyToOne
    @JoinColumn(name = "country_code")
    private CountryCode countryCode;


}

