package com.example.flight_api.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FlightSearchResponseDto {

    private List<FlightDto> departureFlights;
    //Only has a value when querying for return
    private List<FlightDto> returnFlights; 

    public FlightSearchResponseDto(List<FlightDto> departures) {
        this.departureFlights = departures;
    }

    public FlightSearchResponseDto(List<FlightDto> departures, List<FlightDto> returns) {
        this.departureFlights = departures;
        this.returnFlights = returns;
    }

}
