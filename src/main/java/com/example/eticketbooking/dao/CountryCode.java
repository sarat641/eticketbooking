package com.example.eticketbooking.dao;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entity class representing a Country Master in the e-ticket booking system.
 * @author Sarat
 */
@Entity
@Table(name = "COUNTRY_CODES")
@Data
public class CountryCode {
    @Id
    @Column(name = "country_code", length = 3)
    private String countryCode;

    @Column(name = "country_name", nullable = false)
    private String countryName;

    // Getters and setters...
}

