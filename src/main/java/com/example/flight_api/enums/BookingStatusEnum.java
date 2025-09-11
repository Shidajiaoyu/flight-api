package com.example.flight_api.enums;

import lombok.Getter;

/**
 * The status of Booking tbl
 */
@Getter
public enum BookingStatusEnum {

    UPCOMING("UPCOMING"),
    PASTED("PASTED");

    private final String status;

    BookingStatusEnum(String status) {
        this.status = status;
    }

}
