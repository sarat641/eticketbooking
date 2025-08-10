package com.example.eticketbooking.enums;

public enum BookingStatusMessage {
    PENDING("Your booking is pending."),
    CONFIRMED("Your booking has been confirmed."),
    CANCELLED("Your booking has been cancelled."),
    NO_SEATS_AVAILABLE("No seats available for the selected show.");

    private final String message;

    BookingStatusMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
