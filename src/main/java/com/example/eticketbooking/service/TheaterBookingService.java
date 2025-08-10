package com.example.eticketbooking.service;

import com.example.eticketbooking.api.TheaterBooking;
import com.example.eticketbooking.dao.TheaterBookingDao;
import com.example.eticketbooking.dto.BookSeat;
import com.example.eticketbooking.dto.BookingConfirmation;
import com.example.eticketbooking.dto.BrowseTheaterRequest;
import com.example.eticketbooking.dto.ShowTiming;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for managing theater bookings.
 * @author Sarat
 */
@Service
@AllArgsConstructor
public class TheaterBookingService implements TheaterBooking {
    private final TheaterBookingDao theaterBookingDao;

    @Override
    public List<ShowTiming> getTheatersByMovieNameCityAndDate(BrowseTheaterRequest browseTheaterRequest) {
        return theaterBookingDao.getTheatersByMovieNameCityAndDate(browseTheaterRequest.getMovieName(),
                browseTheaterRequest.getCity(), browseTheaterRequest.getDate());
    }

    @Override
    public BookingConfirmation bookSeats(BookSeat bookSeat) {
        return theaterBookingDao.bookSeats(bookSeat);
    }
}
