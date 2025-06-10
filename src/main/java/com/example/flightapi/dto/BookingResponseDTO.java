package com.example.flightapi.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingResponseDTO {
    private String referenceCode;
    private String fromAirport;
    private String toAirport;
    private LocalDateTime departureTime;
    private LocalDateTime bookingTime;
    private String status;
}
