CREATE TABLE users (
    user_id VARCHAR(150) PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(150)  UNIQUE,
    phone_number VARCHAR(15) UNIQUE,
    password_hash VARCHAR(255),
    address TEXT,
    user_type VARCHAR(10) NOT NULL DEFAULT 'B2C', -- 'B2C' or 'B2B'
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_login TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE,
    is_verified BOOLEAN DEFAULT FALSE,
    is_locked BOOLEAN DEFAULT FALSE,
    failed_login_attempts INT DEFAULT 0,
    last_failed_login TIMESTAMP
);

CREATE TABLE MOVIE_LANGUAGES (
    language_id SERIAL PRIMARY KEY,
    language_name VARCHAR(50) NOT NULL UNIQUE
);
CREATE TABLE MOVIE_GENRES (
    genre_id SERIAL PRIMARY KEY,
    genre_name VARCHAR(50) NOT NULL UNIQUE
);
CREATE TABLE MOVIES (
    movie_id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    metadata jsonb, -- Store additional metadata like {"director": "John", "cast": ["Actor1", "Actor2"]}
    criteria jsonb, -- Store criteria like {"age_rating": "PG-13", "genre": ["Action", "Adventure"]}
    release_date DATE,
    duration INT, -- Duration in minutes
    language_id INT REFERENCES MOVIE_LANGUAGES(language_id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE MOVIE_GENRE_RELATION (
    movie_id INT REFERENCES MOVIES(movie_id),
    genre_id INT REFERENCES MOVIE_GENRES(genre_id),
    PRIMARY KEY (movie_id, genre_id)
);
CREATE TABLE COUNTRY_CODES (
    country_code VARCHAR(3) PRIMARY KEY,
    country_name VARCHAR(100) NOT NULL
);
CREATE TABLE CITIES (
    city_id SERIAL PRIMARY KEY,
    city_name VARCHAR(100) NOT NULL,
    is_offers_applicable BOOLEAN DEFAULT FALSE, -- Indicates if offers can be applied in this city
    country_code VARCHAR(3) REFERENCES COUNTRY_CODES(country_code)
);

CREATE TABLE THEATERS (
    theater_id SERIAL PRIMARY KEY,
    theater_name VARCHAR(255) NOT NULL,
    city_id INT REFERENCES CITIES(city_id),
    address TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_enabled BOOLEAN DEFAULT TRUE,-- Indicates if the theater is currently operational
    is_offers_applicable BOOLEAN DEFAULT FALSE -- Indicates if offers can be applied in this theater
);
CREATE TABLE SHOWS (
    show_id SERIAL PRIMARY KEY,
    movie_id INT REFERENCES MOVIES(movie_id),
    theater_id INT REFERENCES THEATERS(theater_id),
    show_time TIMESTAMP NOT NULL,
    show_date DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE USER_BOOKINGS (
    booking_id SERIAL PRIMARY KEY,
    user_id VARCHAR(150) REFERENCES users(email),
    show_id INT REFERENCES SHOWS(show_id),
    booking_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(10, 2) NOT NULL,
    payment_status VARCHAR(20) NOT NULL DEFAULT 'PENDING', -- 'PENDING', 'COMPLETED', 'FAILED'
    payment_method VARCHAR(20) DEFAULT 'ONLINE', -- 'ONLINE', 'UPI','CASH' etc
    is_cancelled BOOLEAN DEFAULT FALSE
);
CREATE TABLE SEATS (
    seat_id SERIAL PRIMARY KEY,
    theater_id INT REFERENCES THEATERS(theater_id),
    seat_number VARCHAR(10) NOT NULL,
    seat_type VARCHAR(20) NOT NULL, -- 'REGULAR', 'VIP', etc
    seat_price DECIMAL(10, 2) NOT NULL,
    is_available BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,


    UNIQUE (theater_id, seat_number) -- Ensure unique seat numbers per theater
);
CREATE TABLE TICKET_BOOKINGS (
    ticket_id SERIAL PRIMARY KEY,
    booking_id INT REFERENCES USER_BOOKINGS(booking_id),
    seat_id INT REFERENCES SEATS(seat_id),
    ticket_price DECIMAL(10, 2) NOT NULL,
    booking_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_refunded BOOLEAN DEFAULT FALSE,
    UNIQUE (booking_id, seat_id) -- Ensure a seat can only be booked once per booking
);
CREATE TABLE DISCOUNTS (
    discount_id SERIAL PRIMARY KEY,
    discount_name VARCHAR(100) NOT NULL,
    description TEXT,
    discount_type VARCHAR(20) NOT NULL, -- e.g., 'TICKET_COUNT', 'SHOW_TIME'
    discount_value DECIMAL(5,2) NOT NULL, -- e.g., 0.5 for 50%, 0.2 for 20%
    criteria jsonb, -- Store criteria like {"ticket_number": 3} or {"show_time": "afternoon"}
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);