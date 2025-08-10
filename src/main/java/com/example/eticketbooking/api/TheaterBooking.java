package com.example.eticketbooking.api;

import com.example.eticketbooking.dto.BookSeat;
import com.example.eticketbooking.dto.BookingConfirmation;
import com.example.eticketbooking.dto.BrowseTheaterRequest;
import com.example.eticketbooking.dto.ShowTiming;

import java.util.List;

/**
 * Interface for theater booking operations.
 * @author Sarat
 */
public interface TheaterBooking {
    List<ShowTiming> getTheatersByMovieNameCityAndDate(BrowseTheaterRequest browseTheaterRequest);
    BookingConfirmation bookSeats(BookSeat bookSeat);
}
