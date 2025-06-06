package com.example.flight_api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDetailResponseDto {

    private String positionlevel;

    private String departure;

    private String destination;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime departureTimestamp;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime destinationTimestamp;

    private BigDecimal basefare;

    private BigDecimal taxfare;

    private BigDecimal totalfare;

}

