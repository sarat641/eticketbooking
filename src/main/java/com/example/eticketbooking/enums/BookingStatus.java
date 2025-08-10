package com.example.eticketbooking.enums;

public enum BookingStatus {
    PENDING( "Pending"),
    CONFIRMED( "Confirmed"),
    CANCELLED("Cancelled");

    private final String description;

    BookingStatus(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}
