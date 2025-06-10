package com.example.flightapi.dto;

import lombok.Data;

@Data
public class FlightSearchDTO {
    private String from;
    private String to;
    private String departureDate;
    private String passager;
}
