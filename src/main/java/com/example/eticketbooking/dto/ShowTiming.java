package com.example.eticketbooking.dto;

import lombok.Data;

/**
 * DTO for show timing details.
 * This class is used to encapsulate the details of a movie show timing.
 * @author Sarat
 */
@Data
public class ShowTiming {
    private String showTime;
    private String date;
    private String movieName;
    private String theaterName;
}
