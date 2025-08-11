
INSERT INTO COUNTRY_CODES (country_code, country_name) VALUES
('IND', 'India'),
('USA', 'United States'),
('GBR', 'United Kingdom');


INSERT INTO CITIES (city_name, is_offers_applicable, country_code) VALUES
('Bangalore', TRUE, 'IND'),
('Mumbai', FALSE, 'IND'),
('New York', TRUE, 'USA');

INSERT INTO MOVIE_LANGUAGES (language_name) VALUES
('English'),
('Hindi'),
('Kannada');

INSERT INTO MOVIE_GENRES (genre_name) VALUES
('Action'),
('Comedy'),
('Drama');

INSERT INTO users (user_id,name, email, phone_number, password_hash, address, user_type, is_active, is_verified)
VALUES
('sarat@gmail.com','Sarat', 'sarat@gmail.com', '1234567890', 'hash1', 'MG Road', 'B2C', TRUE, TRUE),
('babu@gmail.com','Babu','babu@gmail.com' , '2345678901', 'hash2', 'Nagasandhra', 'B2C', TRUE, FALSE),
('satish@gmail.com','Satish', 'satish@gmail.com', '3456789012', 'hash3', 'Rajaji Nagar', 'B2B', FALSE, FALSE);

INSERT INTO MOVIES (title, description, metadata, criteria, release_date, duration, language_id)
VALUES
('Bahubali','Action Movie', '{"director":"Rajamouli"}', '{"age_rating":"A"}', '2025-08-15', 120, 1),
('Housefull 5','Comedy Movie', '{"director":"Tarun Mansukhani"}', '{"age_rating":"U"}', '2025-08-15', 90, 2);

INSERT INTO MOVIE_GENRE_RELATION (movie_id, genre_id) VALUES
(1, 1),
(2, 2);

INSERT INTO THEATERS (theater_name, city_id, address, is_enabled, is_offers_applicable)
VALUES
('PVR Cinemas', 1, 'MG Road', TRUE, TRUE),
('INOX', 2, 'Rajaji Nagar', TRUE, FALSE);

INSERT INTO SHOWS (movie_id, theater_id, show_time, show_date)
VALUES
(1, 1, '2025-08-18 18:00:00', '2025-08-18'),
(2, 2, '2025-08-18 20:00:00', '2025-08-18'),
(1, 1, '2025-08-19 22:00:00', '2025-08-19'),
(2, 2, '2025-08-19 23:00:00', '2025-08-19');

INSERT INTO SEATS (theater_id, seat_number, seat_type,seat_price, is_available)
VALUES
(1, 'A1', 'REGULAR',150.0, TRUE),
(1, 'A2', 'REGULAR', 150.0,TRUE),
(2, 'B1', 'VIP', 250,TRUE);

--INSERT INTO USER_BOOKINGS (user_id, show_id, total_amount, payment_status,payment_method, is_cancelled)
--VALUES
--('sarat@gmail.com', 1, 300.00, 'COMPLETED','UPI', FALSE),
--('babu@gmail.com', 2, 200.00, 'PENDING', 'UPI',FALSE);

--INSERT INTO TICKET_BOOKINGS (booking_id, seat_id, ticket_price, is_refunded)
--VALUES
--(1, 1, 150.00, FALSE),
--(1, 2, 150.00, FALSE),
--(2, 3, 200.00, FALSE);

INSERT INTO DISCOUNTS (discount_name, description, discount_type, discount_value, criteria, is_active)
VALUES
('Summer Offer', '20% off on all tickets', 'TICKET_COUNT', 0.20, '{"ticket_number":2}', TRUE),
('Evening Show', '10% off for evening shows', 'SHOW_TIME', 0.10, '{"show_time":"evening"}', TRUE);
