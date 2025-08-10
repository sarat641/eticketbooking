package com.example.eticketbooking.resource;

import com.example.eticketbooking.api.TheaterBooking;
import com.example.eticketbooking.dto.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @Tag(name = "Browse theatres currently running the show (movie selected) in the city, including show\n" +
            "timing by a chosen date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully fetched theaters",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ShowTiming.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input provided",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorDTO.class)) }) })
    @PostMapping(path = "/theaters")
    public List<ShowTiming> getTheatersByMovieNameCityAndDate(@RequestBody @Valid BrowseTheaterRequest browseTheaterRequest) {
        return theaterBooking.getTheatersByMovieNameCityAndDate(browseTheaterRequest);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully booked seats",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookingConfirmation.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input provided",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorDTO.class)) }) })
    @Tag(name = "Book movie tickets by selecting a theatre, timing, and preferred seats for the day")
    @PostMapping(path = "/theaters/book-seats")
    public BookingConfirmation bookSeats(@RequestBody @Valid BookSeat bookSeat) {
        return theaterBooking.bookSeats(bookSeat);
    }
}
