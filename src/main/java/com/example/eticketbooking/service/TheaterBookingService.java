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

    /**
     * Retrieves theaters showing a specific movie in a given city on a specified date.
     *
     * @param browseTheaterRequest The request containing movie name, city, and date.
     * @return List of ShowTiming containing details of theaters and show timings.
     */
    @Override
    public List<ShowTiming> getTheatersByMovieNameCityAndDate(BrowseTheaterRequest browseTheaterRequest) {
        return theaterBookingDao.getTheatersByMovieNameCityAndDate(browseTheaterRequest.getMovieName(),
                browseTheaterRequest.getCity(), browseTheaterRequest.getDate());
    }
    /**
     * Books seats for a movie show.
     *
     * @param bookSeat The request containing details of the seats to be booked.
     * @return BookingConfirmation containing booking details.
     * @throws EticketGlobalException if no seats are available or if the requested seats are not available.
     */

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
    /**
     * Checks if all requested seats are available.
     *
     * @param listOfSeatsInDB List of seats available in the database.
     * @param listOfSeatsInRequest List of seats requested by the user.
     * @return true if all requested seats are available, false otherwise.
     */
    private boolean isAllSeatsAvailable(List<SeatsDTO> listOfSeatsInDB, List<String> listOfSeatsInRequest) {
        return ApplicationUtil.isTwoListsEqual(listOfSeatsInDB.stream().filter(SeatsDTO::getIsAvailable)
                .map(SeatsDTO::getSeatNumber).toList(), listOfSeatsInRequest);
    }
}
