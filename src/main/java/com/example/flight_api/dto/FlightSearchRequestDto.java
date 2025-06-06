package com.example.flight_api.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightSearchRequestDto {

    // one-way ticket(false) as defalut value
    private boolean isRoundTrip;

    @NotBlank
    private String departureAirport;

    @NotBlank
    private String arrivalAirport;

    @NotNull
    private LocalDate departureDate;

    // round-trip ticket:Required
    private LocalDate returnDate;

    private int passengerCount;

}
