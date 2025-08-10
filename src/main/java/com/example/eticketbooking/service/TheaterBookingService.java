package com.example.eticketbooking.service;

import com.example.eticketbooking.api.TheaterBooking;
import com.example.eticketbooking.dao.TheaterBookingDao;
import com.example.eticketbooking.dto.*;
import com.example.eticketbooking.enums.ResponseEnum;
import com.example.eticketbooking.exceptions.EticketGlobalException;
import com.example.eticketbooking.util.ApplicationUtil;
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
        List<SeatsDTO> listOfSeats= theaterBookingDao.getAvailableSeats(bookSeat);
        if (listOfSeats.isEmpty() || !isAllSeatsAvailable(listOfSeats, bookSeat.getSeatNumbers())) {
            throw new EticketGlobalException(ResponseEnum.NO_SEATS_AVAILABLE.getCode(),
                    ResponseEnum.NO_SEATS_AVAILABLE.getHttpCode(), ResponseEnum.NO_SEATS_AVAILABLE.getMessage());
        }
        String showId = listOfSeats.stream().findAny()
                .map(SeatsDTO::getShowId)
                .map(String::valueOf)
                .orElseThrow(() -> new EticketGlobalException(ResponseEnum.NO_SEATS_AVAILABLE.getCode(),ResponseEnum.NO_SEATS_AVAILABLE.getHttpCode(),
                        ResponseEnum.NO_SEATS_AVAILABLE.getMessage()));

        return theaterBookingDao.bookSeats(bookSeat, listOfSeats, showId);
    }
    private boolean isAllSeatsAvailable(List<SeatsDTO> listOfSeatsInDB, List<String> listOfSeatsInRequest) {
        return ApplicationUtil.isTwoListsEqual(listOfSeatsInDB.stream().filter(SeatsDTO::getIsAvailable)
                .map(SeatsDTO::getSeatNumber).toList(), listOfSeatsInRequest);
    }
}
