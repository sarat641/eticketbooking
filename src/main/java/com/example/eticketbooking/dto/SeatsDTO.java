package com.example.eticketbooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * DTO for seat details.
 * This class is used to encapsulate the details of a seat in a theater.
 * @author Sarat
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatsDTO {

    private Integer seatId;
    private Integer theater;
    private String seatNumber;
    private String seatType;
    private BigDecimal seatPrice;
    private Boolean isAvailable;
    private Integer showId;
}
