package com.flight.flight_api.utils;

/**
 * 订单状态枚举
 */
public enum BookingStatus {
    PENDING("PENDING"),
    PAID("PAID"),
    CANCELLED("CANCELLED"),
    COMPLETED("COMPLETED");

    private final String description;

    BookingStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
