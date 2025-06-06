package com.flight.flight_api.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

import lombok.Data;

// 航班信息
@Data
public class FlightDto {

    // 航班Id
    private Long flightId;

    // 航班号
    private String flightNumber;

    // 出发地机场Id
    private Long departureAirportId;

    // 出发日期
    private Date departureDate;

    // 出发时刻
    private Time departureTime;

    // 到达地机场Id
    private Long destinationAirportId;

    // 到达日期
    private Date arrivalDate;

    // 到达时刻
    private Time arrivalTime;

    // 价格
    private BigDecimal totalPrice;

}
