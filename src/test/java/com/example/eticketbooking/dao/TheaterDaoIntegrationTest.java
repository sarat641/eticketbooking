package com.example.eticketbooking.dao;

import com.example.eticketbooking.dto.BookSeat;
import com.example.eticketbooking.dto.BookingConfirmation;
import com.example.eticketbooking.dto.SeatsDTO;
import com.example.eticketbooking.dto.ShowTiming;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Import(TheaterBookingDao.class)
public class TheaterDaoIntegrationTest {
    @Autowired
    private TheaterBookingDao theaterDao;

    @Test
    void testGetTheatersByMovieNameCityAndDate() {
        List<ShowTiming> results = theaterDao.getTheatersByMovieNameCityAndDate(
                "Bahubali", "Bangalore", LocalDate.of(2025, 8, 18)
        );

        assertNotNull(results);
        assertEquals(2, results.size());
        assertEquals("PVR Cinemas", results.get(0).getTheaterName());
        assertEquals("Bahubali", results.get(0).getMovieName());
    }

    @Test
    void testReturnsEmptyForNonExistingCity() {
        List<ShowTiming> results = theaterDao.getTheatersByMovieNameCityAndDate(
                "Bahubali", "Delhi", LocalDate.of(2025, 8, 18)
        );
        assertTrue(results.isEmpty());
    }

    @Test
    void testReturnsEmptyForNonExistingMovie() {
        List<ShowTiming> results = theaterDao.getTheatersByMovieNameCityAndDate(
                "NonExistingMovie", "Bangalore", LocalDate.of(2025, 8, 18)
        );
        assertTrue(results.isEmpty());
    }

    @Test
    void testReturnsEmptyForNonExistingDate() {
        List<ShowTiming> results = theaterDao.getTheatersByMovieNameCityAndDate(
                "Bahubali", "Bangalore", LocalDate.of(2025, 1, 1)
        );
        assertTrue(results.isEmpty());
    }

    @Test
    void testReturnsEmptyForNonExistingMovieAndCity() {
        List<ShowTiming> results = theaterDao.getTheatersByMovieNameCityAndDate(
                "NonExistingMovie", "NonExistingCity", LocalDate.of(2025, 8, 18)
        );
        assertTrue(results.isEmpty());
    }

    @Test
    void testReturnsEmptyForNonExistingMovieAndDate() {
        List<ShowTiming> results = theaterDao.getTheatersByMovieNameCityAndDate(
                "NonExistingMovie", "Bangalore", LocalDate.of(2025, 1, 1)
        );
        assertTrue(results.isEmpty());
    }

    @Test
    void testReturnsEmptyForNonExistingCityAndDate() {
        List<ShowTiming> results = theaterDao.getTheatersByMovieNameCityAndDate(
                "Bahubali", "NonExistingCity", LocalDate.of(2025, 1, 1)
        );
        assertTrue(results.isEmpty());
    }

    @Test
    void testReturnsEmptyForNonExistingMovieCityAndDate() {
        List<ShowTiming> results = theaterDao.getTheatersByMovieNameCityAndDate(
                "NonExistingMovie", "NonExistingCity", LocalDate.of(2025, 1, 1)
        );
        assertTrue(results.isEmpty());
    }

    @Test
    void testReturnsEmptyForNullMovieName() {
        List<ShowTiming> results = theaterDao.getTheatersByMovieNameCityAndDate(
                null, "Bangalore", LocalDate.of(2025, 8, 18)
        );
        assertTrue(results.isEmpty());
    }

    @Test
    void testBookSeatsWithValidData() {
        // Assuming BookSeat and SeatsDTO are properly defined and available
        BookSeat bookSeat = new BookSeat();
        bookSeat.setUserId("sarat@gmail.com");
        bookSeat.setPaymentMethod("Credit Card");
        bookSeat.setSeatNumbers(List.of("A1", "A2"));

        BookingConfirmation confirmation = theaterDao.bookSeats(bookSeat,
                List.of(new SeatsDTO(1, 1, "A1", "Regualar", new BigDecimal(150), true, 1)), "1");
        assertNotNull(confirmation);
        assertEquals("CONFIRMED", confirmation.getBookingStatus());
    }

    @Test
    void testBookSeatsWithInvalidData() {
        BookSeat bookSeat = new BookSeat();
        bookSeat.setUserId("sarat@gmail.com");
        bookSeat.setPaymentMethod("Credit Card");
        bookSeat.setSeatNumbers(List.of("A1", "A2"));
        // Assuming no seats available for the given show
        List<SeatsDTO> availableSeats = theaterDao.getAvailableSeats(bookSeat);
        assertTrue(availableSeats.isEmpty(), "Expected no available seats for the given booking details");
    }

    @Test
    void testGetAvailableSeatsWithInvalidData() {
        BookSeat bookSeat = new BookSeat();
        bookSeat.setCity("NonExistingCity");
        bookSeat.setTheaterName("NonExistingTheater");
        bookSeat.setMovieName("NonExistingMovie");
        bookSeat.setShowTime(LocalDate.of(2025, 1, 1).atStartOfDay());
        bookSeat.setSeatNumbers(List.of("A1", "A2"));

        List<SeatsDTO> availableSeats = theaterDao.getAvailableSeats(bookSeat);
        assertTrue(availableSeats.isEmpty(), "Expected no available seats for the given booking details");
    }

    @Test
    void testGetAvailableSeatsWithNullData() {
        BookSeat bookSeat = new BookSeat();
        bookSeat.setCity(null);
        bookSeat.setTheaterName(null);
        bookSeat.setMovieName(null);
        bookSeat.setShowTime(null);
        bookSeat.setSeatNumbers(null);
        List<SeatsDTO> availableSeats = theaterDao.getAvailableSeats(bookSeat);
        assertTrue(availableSeats.isEmpty(), "Expected no available seats for null booking details");
    }


    @Test
    void testGetAvailableSeatsWithInvalidShowTime() {
        BookSeat bookSeat = new BookSeat();
        bookSeat.setCity("Bangalore");
        bookSeat.setTheaterName("PVR Cinemas");
        bookSeat.setMovieName("Bahubali");
        bookSeat.setShowTime(LocalDate.of(2025, 1, 1).atStartOfDay());
        bookSeat.setSeatNumbers(List.of("A1", "A2"));
        List<SeatsDTO> availableSeats = theaterDao.getAvailableSeats(bookSeat);
        assertTrue(availableSeats.isEmpty(), "Expected no available seats for invalid show time");
    }

}
