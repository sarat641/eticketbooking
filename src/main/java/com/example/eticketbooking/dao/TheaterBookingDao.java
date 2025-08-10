package com.example.eticketbooking.dao;

import com.example.eticketbooking.dto.BookSeat;
import com.example.eticketbooking.dto.BookingConfirmation;
import com.example.eticketbooking.dto.SeatsDTO;
import com.example.eticketbooking.dto.ShowTiming;
import com.example.eticketbooking.enums.BookingStatus;
import com.example.eticketbooking.enums.BookingStatusMessage;
import com.example.eticketbooking.enums.ResponseEnum;
import com.example.eticketbooking.exceptions.EticketGlobalException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Data Access Object for Theater Booking
 *
 * @author Sarat
 */
@Repository
@AllArgsConstructor
public class TheaterBookingDao {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<ShowTiming> getTheatersByMovieNameCityAndDate(String movieName, String city, LocalDate date) {

        String sql = """
                SELECT t.theater_id,t.theater_name,t.address,s.show_id,s.show_time,s.show_date FROM THEATERS t 
                JOIN SHOWS s ON t.theater_id = s.theater_id 
                JOIN MOVIES m ON s.movie_id = m.movie_id 
                JOIN CITIES c ON t.city_id = c.city_id 
                WHERE c.city_name = :city_name AND m.title = :movie_title 
                AND s.show_date = :chosen_date AND t.is_enabled = TRUE 
                ORDER BY s.show_time
                """;
        MapSqlParameterSource params = new MapSqlParameterSource("city_name", city);
        params.addValue("movie_title", movieName);
        params.addValue("chosen_date", date);

        return namedParameterJdbcTemplate.query(
                sql,
                params,
                (rs, rowNum) -> {
                    ShowTiming showTiming = new ShowTiming();
                    showTiming.setShowTime(rs.getString("show_time"));
                    showTiming.setDate(rs.getString("show_date"));
                    showTiming.setMovieName(movieName);
                    showTiming.setTheaterName(rs.getString("theater_name"));
                    return showTiming;
                }
        );
    }

    @Transactional
    public BookingConfirmation bookSeats(BookSeat bookSeat) {

        List<SeatsDTO> listOfSeats= getSeats(bookSeat);
        BookingConfirmation bookingConfirmation = new BookingConfirmation();

        String showId = listOfSeats.stream().findAny()
                .map(SeatsDTO::getShowId)
                .map(String::valueOf)
                .orElseThrow(() -> new EticketGlobalException(ResponseEnum.NO_SEATS_AVAILABLE.getCode(),ResponseEnum.NO_SEATS_AVAILABLE.getHttpCode(),
                        ResponseEnum.NO_SEATS_AVAILABLE.getMessage()));
        String userBookingSQL = """
                INSERT INTO USER_BOOKINGS (show_id, user_id, booking_time, total_amount, payment_status, payment_method)
                VALUES (:show_id, :user_id,  NOW(), :total_amount,'Confirmed', :payment_method)
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        BigDecimal totalAmount= listOfSeats.stream().map(SeatsDTO::getSeatPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("show_id", showId)
                .addValue("user_id", bookSeat.getUserId())
                .addValue("total_amount", totalAmount)
                .addValue("payment_method", bookSeat.getPaymentMethod());
        namedParameterJdbcTemplate.update(userBookingSQL, params, keyHolder, new String[]{"booking_id"});
        Number generatedId = keyHolder.getKey();

        if (generatedId != null) {
            long userBookingId = generatedId.longValue();

            String sql = """
                    INSERT INTO TICKET_BOOKINGS ( seat_id,  booking_id, booking_time,ticket_price)
                    VALUES ( :seat_id,  :booking_id, NOW(),:ticket_price)
                    """;
            List<MapSqlParameterSource> paramsList = listOfSeats.stream()
                    .map(seatNumber -> new MapSqlParameterSource()
                            .addValue("ticket_price", seatNumber.getSeatPrice())
                            .addValue("seat_id", seatNumber.getSeatId())
                            .addValue("booking_id", userBookingId))
                    .toList();
            namedParameterJdbcTemplate.batchUpdate(sql, paramsList.toArray(new SqlParameterSource[0]));
            bookingConfirmation.setBookingId(String.valueOf(userBookingId));
            bookingConfirmation.setUserId(bookSeat.getUserId());
            bookingConfirmation.setShowId(showId);
            bookingConfirmation.setTheaterName(bookSeat.getTheaterName());
            bookingConfirmation.setMovieName(bookSeat.getMovieName());
            bookingConfirmation.setDate(bookSeat.getDate());
            bookingConfirmation.setShowTime(bookSeat.getShowTime());
            bookingConfirmation.setNumberOfSeats(listOfSeats.size());
            bookingConfirmation.setSeatType(bookSeat.getSeatType());
            bookingConfirmation.setSeatNumbers(listOfSeats.stream().map(SeatsDTO::getSeatNumber).toList());
            bookingConfirmation.setBookingStatus(BookingStatus.CONFIRMED.name());
            bookingConfirmation.setBookingTimestamp(java.time.OffsetDateTime.now().toString());
            bookingConfirmation.setTotalAmount(totalAmount
                    .toString());
            bookingConfirmation.setPaymentMethod(bookSeat.getPaymentMethod());
            bookingConfirmation.setConfirmationMessage(BookingStatusMessage.CONFIRMED.getMessage());
        }


        return bookingConfirmation;
    }
    public List<SeatsDTO> getSeats(BookSeat bookSeat) {

        String getSeatAvailabilityFromTheaterNameCityMovieNameAndShowTimeSQL = """
                SELECT s.is_available,s.seat_id,s.seat_number,s.seat_type,s.seat_price,sh.show_id FROM SEATS s 
                    JOIN SHOWS sh ON sh.theater_id = s.theater_id 
                    JOIN MOVIES m ON sh.movie_id = m.movie_id 
                    JOIN THEATERS t ON sh.theater_id = t.theater_id 
                    JOIN CITIES c ON t.city_id = c.city_id 
                WHERE c.city_name = :city_name 
                    AND t.theater_name = :theater_name 
                    AND m.title = :movie_name 
                    AND sh.show_time = :show_time 
                    AND s.seat_id NOT IN 
                    ( SELECT tb.seat_id FROM TICKET_BOOKINGS tb JOIN USER_BOOKINGS ub ON tb.booking_id = ub.booking_id 
                WHERE ub.is_cancelled = FALSE AND ub.show_id = sh.show_id ) 
                      AND s.seat_number in (:seat_numbers) and s.is_available = TRUE 
                      ORDER BY s.seat_number
                """;



        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("city_name", bookSeat.getCity())
                .addValue("theater_name", bookSeat.getTheaterName())
                .addValue("movie_name", bookSeat.getMovieName())
                .addValue("show_time", bookSeat.getShowTime())
                .addValue("seat_numbers", bookSeat.getSeatNumbers());

        return namedParameterJdbcTemplate.query(
                getSeatAvailabilityFromTheaterNameCityMovieNameAndShowTimeSQL,
                params,
                (rs, rowNum) -> {
                    SeatsDTO seatsDTO = new SeatsDTO();
                    seatsDTO.setSeatId(rs.getInt("seat_id"));
                    seatsDTO.setSeatPrice(rs.getBigDecimal("seat_price"));
                    seatsDTO.setIsAvailable(rs.getBoolean("is_available"));
                    seatsDTO.setSeatNumber(rs.getString("seat_number"));
                    seatsDTO.setSeatType(rs.getString("seat_type"));
                    seatsDTO.setShowId(rs.getInt("show_id"));
                    return seatsDTO;
                }
        );
    }

}
