package com.example.eticketbooking.resource;

import com.example.eticketbooking.api.TheaterBooking;
import com.example.eticketbooking.dto.BookSeat;
import com.example.eticketbooking.dto.BookingConfirmation;
import com.example.eticketbooking.dto.BrowseTheaterRequest;
import com.example.eticketbooking.dto.ShowTiming;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * Controller for handling theater booking related requests.
 * @author Sarat
 */
@RestController
@AllArgsConstructor
public class TheaterBookingController {

    private final TheaterBooking theaterBooking;

    @PostMapping(path = "/theaters")
    public List<ShowTiming> getTheatersByMovieNameCityAndDate(@RequestBody @Valid BrowseTheaterRequest browseTheaterRequest) {
        return theaterBooking.getTheatersByMovieNameCityAndDate(browseTheaterRequest);
    }
    @PostMapping(path = "/theaters/book-seats")
    public BookingConfirmation bookSeats(@RequestBody @Valid BookSeat bookSeat) {
        return theaterBooking.bookSeats(bookSeat);
    }
}
