package com.example.flight_api.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDetailDto {

    private String position_level;

    private String departure;

    private String destination;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime departureTimestamp;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime destinationTimestamp;

}
