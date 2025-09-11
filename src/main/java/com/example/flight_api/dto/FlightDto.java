package com.example.flight_api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto {

    private Long id;

    private String flight_number;

    private String departure;

    private String destination;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime departureTimestamp;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime destinationTimestamp;

    // destinationTimestamp - departureTimestamp
    private String duration;

    private BigDecimal price;
}
