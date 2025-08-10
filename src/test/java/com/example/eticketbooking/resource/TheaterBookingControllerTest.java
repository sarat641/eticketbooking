package com.example.eticketbooking.resource;

import com.example.eticketbooking.api.TheaterBooking;
import com.example.eticketbooking.dto.BrowseTheaterRequest;
import com.example.eticketbooking.dto.ShowTiming;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class TheaterBookingControllerTest {

    @Mock
    private TheaterBooking theaterBooking;

    @InjectMocks
    private TheaterBookingController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getTheatersByMovieNameCityAndDate_returnsShowTimings() {
        BrowseTheaterRequest request = new BrowseTheaterRequest();
        // Set fields as needed, e.g.:
        // request.setMovieName("Test Movie");
        // request.setCity("Test City");
        // request.setDate(LocalDate.now());

        ShowTiming show1 = new ShowTiming();
        ShowTiming show2 = new ShowTiming();
        List<ShowTiming> expected = Arrays.asList(show1, show2);

        when(theaterBooking.getTheatersByMovieNameCityAndDate(ArgumentMatchers.any(BrowseTheaterRequest.class)))
                .thenReturn(expected);

        List<ShowTiming> result = controller.getTheatersByMovieNameCityAndDate(request);

        assertEquals(expected, result);
    }
}

