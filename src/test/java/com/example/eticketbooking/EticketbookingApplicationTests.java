package com.example.eticketbooking;

import com.example.eticketbooking.api.TheaterBooking;
import com.example.eticketbooking.dto.BookSeat;
import com.example.eticketbooking.dto.BookingConfirmation;
import com.example.eticketbooking.dto.BrowseTheaterRequest;
import com.example.eticketbooking.dto.ShowTiming;
import com.example.eticketbooking.resource.TheaterBookingController;
import com.example.eticketbooking.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = {MockServletContext.class, TestContext.class})
@AutoConfigureMockMvc
class EticketbookingApplicationTests {

    private final String URL = "/hello/eticket";
    private final String URL2 = "/theaters";
    private final String URL3 = "/theaters/book-seats";
    @Mock
    TheaterBooking theaterBooking;
    @Autowired
    MessageSource messageSource;
    private MockMvc mvc;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(new TheaterBookingController(theaterBooking)).build();

    }

    @Test
    public void getIndex() throws Exception {
        mvc.perform(get(URL).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello, welcome to the e-ticket booking system!")));
    }

    @Test
    public void testGetTheatersByMovieNameCityAndDate() throws Exception {
        BrowseTheaterRequest request = new BrowseTheaterRequest();
        request.setMovieName("Bahubali");
        request.setCity("Bangalore");
        request.setDate(LocalDate.parse("10-08-2026", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        ShowTiming showTiming = new ShowTiming();
        showTiming.setMovieName("Bahubali");
        showTiming.setTheaterName("PVR");
        List<ShowTiming> showTimingList = List.of(showTiming);
        when(theaterBooking.getTheatersByMovieNameCityAndDate(request)).thenReturn(showTimingList);
        mvc.perform(post(URL2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(request)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetTheatersByMovieNameCityAndDateValidationMovieNameMissing() throws Exception {
        BrowseTheaterRequest request = new BrowseTheaterRequest();
//		request.setMovieName("Bahubali");
        request.setCity("Bangalore");
        request.setDate(LocalDate.parse("10-08-2026", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        ShowTiming showTiming = new ShowTiming();
        showTiming.setMovieName("Bahubali");
        showTiming.setTheaterName("PVR");
        List<ShowTiming> showTimingList = List.of(showTiming);
        when(theaterBooking.getTheatersByMovieNameCityAndDate(request)).thenReturn(showTimingList);
        mvc.perform(post("/theaters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetTheatersByMovieNameCityAndDateValidationCityNameMissing() throws Exception {
        BrowseTheaterRequest request = new BrowseTheaterRequest();
        request.setMovieName("Bahubali");
        request.setDate(LocalDate.parse("10-08-2026", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        ShowTiming showTiming = new ShowTiming();
        showTiming.setMovieName("Bahubali");
        showTiming.setTheaterName("PVR");
        List<ShowTiming> showTimingList = List.of(showTiming);
        when(theaterBooking.getTheatersByMovieNameCityAndDate(request)).thenReturn(showTimingList);
        mvc.perform(post(URL2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetTheatersByMovieNameCityAndDateValidationDateMissing() throws Exception {
        BrowseTheaterRequest request = new BrowseTheaterRequest();
        request.setMovieName("Bahubali");
        ShowTiming showTiming = new ShowTiming();
        showTiming.setMovieName("Bahubali");
        showTiming.setTheaterName("PVR");
        List<ShowTiming> showTimingList = List.of(showTiming);
        when(theaterBooking.getTheatersByMovieNameCityAndDate(request)).thenReturn(showTimingList);
        mvc.perform(post(URL2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetTheatersByMovieNameCityAndDateValidationDateInPast() throws Exception {
        BrowseTheaterRequest request = new BrowseTheaterRequest();
        request.setMovieName("Bahubali");
        request.setCity("Bangalore");
        request.setDate(LocalDate.parse("10-08-2020", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        ShowTiming showTiming = new ShowTiming();
        showTiming.setMovieName("Bahubali");
        showTiming.setTheaterName("PVR");
        List<ShowTiming> showTimingList = List.of(showTiming);
        when(theaterBooking.getTheatersByMovieNameCityAndDate(request)).thenReturn(showTimingList);
        mvc.perform(post(URL2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetTheatersByMovieNameCityAndDateValidationMovieNameTooShort() throws Exception {
        BrowseTheaterRequest request = new BrowseTheaterRequest();
        request.setMovieName("Ba");
        request.setCity("Bangalore");
        request.setDate(LocalDate.parse("10-08-2026", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        ShowTiming showTiming = new ShowTiming();
        showTiming.setMovieName("Bahubali");
        showTiming.setTheaterName("PVR");
        List<ShowTiming> showTimingList = List.of(showTiming);
        when(theaterBooking.getTheatersByMovieNameCityAndDate(request)).thenReturn(showTimingList);
        mvc.perform(post(URL2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetTheatersByMovieNameCityAndDateValidationCityNameTooShort() throws Exception {
        BrowseTheaterRequest request = new BrowseTheaterRequest();
        request.setMovieName("Bahubali");
        request.setCity("Ba");
        request.setDate(LocalDate.parse("10-08-2026", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        ShowTiming showTiming = new ShowTiming();
        showTiming.setMovieName("Bahubali");
        showTiming.setTheaterName("PVR");
        List<ShowTiming> showTimingList = List.of(showTiming);
        when(theaterBooking.getTheatersByMovieNameCityAndDate(request)).thenReturn(showTimingList);
        mvc.perform(post(URL2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetTheatersByMovieNameCityAndDateValidationDateFormat() throws Exception {
        BrowseTheaterRequest request = new BrowseTheaterRequest();
        request.setMovieName("Bahubali");
        request.setCity("Bangalore");
        request.setDate(LocalDate.parse("10-08-2026", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        ShowTiming showTiming = new ShowTiming();
        showTiming.setMovieName("Bahubali");
        showTiming.setTheaterName("PVR");
        List<ShowTiming> showTimingList = List.of(showTiming);
        when(theaterBooking.getTheatersByMovieNameCityAndDate(request)).thenReturn(showTimingList);
        mvc.perform(post(URL2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(request)))
                .andExpect(status().isOk());
    }

    @Test
    public void testBookSeats() throws Exception {
        BookSeat bookSeat = new BookSeat();
        bookSeat.setMovieName("Bahubali");
        bookSeat.setTheaterName("PVR Cinemas");
        bookSeat.setCity("Bangalore");
        bookSeat.setShowTime(LocalDateTime.parse("10-08-2026 22:00:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        bookSeat.setDate("10-08-2025");
        bookSeat.setSeatNumbers(List.of("A1", "A2"));
        bookSeat.setNumberOfSeats(2);
        bookSeat.setSeatType("Regular");
        bookSeat.setUserId("sarat@gmail.com");
        bookSeat.setPaymentMethod("Credit Card");
        BookingConfirmation bookingConfirmation = new BookingConfirmation();
        bookingConfirmation.setBookingId("12345");
        bookingConfirmation.setMovieName("Bahubali");
        bookingConfirmation.setTheaterName("PVR Cinemas");
        bookingConfirmation.setShowTime(LocalDate.parse("10-08-2025", DateTimeFormatter.ofPattern("dd-MM-yyyy")).atTime(22, 0));
        bookingConfirmation.setSeatNumbers(List.of("A1", "A2"));
        bookingConfirmation.setNumberOfSeats(2);
        bookingConfirmation.setSeatType("Regular");
        bookingConfirmation.setUserId("sarat@gmail.com");
        bookingConfirmation.setPaymentMethod("Credit Card");

        when(theaterBooking.bookSeats(bookSeat)).thenReturn(bookingConfirmation);

            mvc.perform(post(URL3)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(TestUtil.convertObjectToJsonBytes(bookSeat)))
                    .andExpect(status().isOk());

    }
    @Test
    public void testBookSeatsValidationMovieNameMissing() throws Exception {
        BookSeat bookSeat = new BookSeat();
        // bookSeat.setMovieName("Bahubali");
        bookSeat.setTheaterName("PVR Cinemas");
        bookSeat.setCity("Bangalore");
        bookSeat.setShowTime(LocalDateTime.parse("10-08-2026 22:00:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        bookSeat.setDate("10-08-2025");
        bookSeat.setSeatNumbers(List.of("A1", "A2"));
        bookSeat.setNumberOfSeats(2);
        bookSeat.setSeatType("Regular");
        bookSeat.setUserId("sarat@gmail.com");
        bookSeat.setPaymentMethod("Credit Card");
        BookingConfirmation bookingConfirmation = new BookingConfirmation();
        bookingConfirmation.setBookingId("12345");
        bookingConfirmation.setMovieName("Bahubali");
        bookingConfirmation.setTheaterName("PVR Cinemas");
        bookingConfirmation.setShowTime((LocalDateTime.parse("10-08-2026 22:00:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))));
        bookingConfirmation.setSeatNumbers(List.of("A1", "A2"));
        bookingConfirmation.setNumberOfSeats(2);
        bookingConfirmation.setSeatType("Regular");
        bookingConfirmation.setUserId("sarat@gmail.com");
        bookingConfirmation.setPaymentMethod("Credit Card");
        when(theaterBooking.bookSeats(bookSeat)).thenReturn(bookingConfirmation);
        mvc.perform(post(URL3)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(bookSeat)))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testBookSeatsValidationTheaterNameMissing() throws Exception {
        BookSeat bookSeat = new BookSeat();
        bookSeat.setMovieName("Bahubali");
        // bookSeat.setTheaterName("PVR Cinemas");
        bookSeat.setCity("Bangalore");
        bookSeat.setShowTime(LocalDateTime.parse("10-08-2026 22:00:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        bookSeat.setDate("10-08-2025");
        bookSeat.setSeatNumbers(List.of("A1", "A2"));
        bookSeat.setNumberOfSeats(2);
        bookSeat.setSeatType("Regular");
        bookSeat.setUserId("test@gmail.com");
        bookSeat.setPaymentMethod("Credit Card");
        BookingConfirmation bookingConfirmation = new BookingConfirmation();
        bookingConfirmation.setBookingId("12345");
        bookingConfirmation.setMovieName("Bahubali");
        bookingConfirmation.setTheaterName("PVR Cinemas");
        bookingConfirmation.setShowTime(LocalDateTime.parse("10-08-2026 22:00:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        bookingConfirmation.setSeatNumbers(List.of("A1", "A2"));
        bookingConfirmation.setNumberOfSeats(2);
        bookingConfirmation.setSeatType("Regular");
        bookingConfirmation.setUserId("test@gmail.com");
        bookingConfirmation.setPaymentMethod("Credit Card");
        when(theaterBooking.bookSeats(bookSeat)).thenReturn(bookingConfirmation);
        mvc.perform(post(URL3)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(bookSeat)))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testBookSeatsValidationCityNameMissing() throws Exception {
        BookSeat bookSeat = new BookSeat();
        bookSeat.setMovieName("Bahubali");
        bookSeat.setTheaterName("PVR Cinemas");
        // bookSeat.setCity("Bangalore");
        bookSeat.setShowTime(LocalDateTime.parse("10-08-2026 22:00:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        bookSeat.setDate("10-08-2025");
        bookSeat.setSeatNumbers(List.of("A1", "A2"));
        bookSeat.setNumberOfSeats(2);
        bookSeat.setSeatType("Regular");
        bookSeat.setUserId("test@gmail.com");
        bookSeat.setPaymentMethod("Credit Card");
        BookingConfirmation bookingConfirmation = new BookingConfirmation();
        bookingConfirmation.setBookingId("12345");
        bookingConfirmation.setMovieName("Bahubali");
        bookingConfirmation.setTheaterName("PVR Cinemas");
        bookingConfirmation.setShowTime(LocalDateTime.parse("10-08-2026 22:00:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        bookingConfirmation.setSeatNumbers(List.of("A1", "A2"));
        bookingConfirmation.setNumberOfSeats(2);
        bookingConfirmation.setSeatType("Regular");
        bookingConfirmation.setUserId("test@gmail.com");
        bookingConfirmation.setPaymentMethod("Credit Card");
        when(theaterBooking.bookSeats(bookSeat)).thenReturn(bookingConfirmation);
        mvc.perform(post(URL3)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(bookSeat)))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testBookSeatsValidationShowTimeMissing() throws Exception {
        BookSeat bookSeat = new BookSeat();
        bookSeat.setMovieName("Bahubali");
        bookSeat.setTheaterName("PVR Cinemas");
        bookSeat.setCity("Bangalore");
        // bookSeat.setShowTime(LocalDateTime.parse("10-08-2026 22:00:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        bookSeat.setDate("10-08-2025");
        bookSeat.setSeatNumbers(List.of("A1", "A2"));
        bookSeat.setNumberOfSeats(2);
        bookSeat.setSeatType("Regular");
        bookSeat.setUserId("test@gmail.com");
        bookSeat.setPaymentMethod("Credit Card");
        BookingConfirmation bookingConfirmation = new BookingConfirmation();
        bookingConfirmation.setBookingId("12345");
        bookingConfirmation.setMovieName("Bahubali");
        bookingConfirmation.setTheaterName("PVR Cinemas");
        bookingConfirmation.setShowTime(LocalDateTime.parse("10-08-2026 22:00:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        bookingConfirmation.setSeatNumbers(List.of("A1", "A2"));
        bookingConfirmation.setNumberOfSeats(2);
        bookingConfirmation.setSeatType("Regular");
        bookingConfirmation.setUserId("test@gmail.com");
        bookingConfirmation.setPaymentMethod("Credit Card");
        when(theaterBooking.bookSeats(bookSeat)).thenReturn(bookingConfirmation);
        mvc.perform(post(URL3)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(bookSeat)))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testBookSeatsValidationShowTimePast() throws Exception {
        BookSeat bookSeat = new BookSeat();
        bookSeat.setMovieName("Bahubali");
        bookSeat.setTheaterName("PVR Cinemas");
        bookSeat.setCity("Bangalore");
        bookSeat.setShowTime(LocalDateTime.parse("10-08-2020 22:00:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        bookSeat.setDate("10-08-2025");
        bookSeat.setSeatNumbers(List.of("A1", "A2"));
        bookSeat.setNumberOfSeats(2);
        bookSeat.setSeatType("Regular");
        bookSeat.setUserId("test@gmail.com");
        bookSeat.setPaymentMethod("Credit Card");
        BookingConfirmation bookingConfirmation = new BookingConfirmation();
        bookingConfirmation.setBookingId("12345");
        bookingConfirmation.setMovieName("Bahubali");
        bookingConfirmation.setTheaterName("PVR Cinemas");
        bookingConfirmation.setShowTime(LocalDateTime.parse("10-08-2026 22:00:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        bookingConfirmation.setSeatNumbers(List.of("A1", "A2"));
        bookingConfirmation.setNumberOfSeats(2);
        bookingConfirmation.setSeatType("Regular");
        bookingConfirmation.setUserId("test@gmail.com");
        bookingConfirmation.setPaymentMethod("Credit Card");
        when(theaterBooking.bookSeats(bookSeat)).thenReturn(bookingConfirmation);
        mvc.perform(post(URL3)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(bookSeat)))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testBookSeatsValidationMovieNameTooShort() throws Exception {
        BookSeat bookSeat = new BookSeat();
        bookSeat.setMovieName("Ba");
        bookSeat.setTheaterName("PVR Cinemas");
        bookSeat.setCity("Bangalore");
        bookSeat.setShowTime(LocalDateTime.parse("10-08-2026 22:00:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        bookSeat.setDate("10-08-2025");
        bookSeat.setSeatNumbers(List.of("A1", "A2"));
        bookSeat.setNumberOfSeats(2);
        bookSeat.setSeatType("Regular");
        bookSeat.setUserId("test@gmail.com");
        bookSeat.setPaymentMethod("Credit Card");
        BookingConfirmation bookingConfirmation = new BookingConfirmation();
        bookingConfirmation.setBookingId("12345");
        bookingConfirmation.setMovieName("Bahubali");
        bookingConfirmation.setTheaterName("PVR Cinemas");
        bookingConfirmation.setShowTime(LocalDateTime.parse("10-08-2026 22:00:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        bookingConfirmation.setSeatNumbers(List.of("A1", "A2"));
        bookingConfirmation.setNumberOfSeats(2);
        bookingConfirmation.setSeatType("Regular");
        bookingConfirmation.setUserId("test@gmail.com");
        bookingConfirmation.setPaymentMethod("Credit Card");
        when(theaterBooking.bookSeats(bookSeat)).thenReturn(bookingConfirmation);
        mvc.perform(post(URL3)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(bookSeat)))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testBookSeatsValidationTheaterNameTooShort() throws Exception {
        BookSeat bookSeat = new BookSeat();
        bookSeat.setMovieName("Bahubali");
        bookSeat.setTheaterName("PV");
        bookSeat.setCity("Bangalore");
        bookSeat.setShowTime(LocalDateTime.parse("10-08-2026 22:00:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        bookSeat.setDate("10-08-2025");
        bookSeat.setSeatNumbers(List.of("A1", "A2"));
        bookSeat.setNumberOfSeats(2);
        bookSeat.setSeatType("Regular");
        bookSeat.setUserId("test@gmail.com");
        bookSeat.setPaymentMethod("Credit Card");
        BookingConfirmation bookingConfirmation = new BookingConfirmation();
        bookingConfirmation.setBookingId("12345");
        bookingConfirmation.setMovieName("Bahubali");
        bookingConfirmation.setTheaterName("PVR Cinemas");
        bookingConfirmation.setShowTime(LocalDateTime.parse("10-08-2026 22:00:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        bookingConfirmation.setSeatNumbers(List.of("A1", "A2"));
        bookingConfirmation.setNumberOfSeats(2);
        bookingConfirmation.setSeatType("Regular");
        bookingConfirmation.setUserId("test@gmail.com");
        bookingConfirmation.setPaymentMethod("Credit Card");
        when(theaterBooking.bookSeats(bookSeat)).thenReturn(bookingConfirmation);
        mvc.perform(post(URL3)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(bookSeat)))
                        .andExpect(status().isBadRequest());
    }

}
