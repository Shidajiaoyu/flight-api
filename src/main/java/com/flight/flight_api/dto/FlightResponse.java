package com.flight.flight_api.dto;

import java.util.List;

import lombok.Data;

// 航班检索响应Dto
@Data
public class FlightResponse {

    // 出发地
    private String departure;

    // 到达地
    private String arrival;

    // 乘客数
    private int passengers;

    // 航班信息
    private List<FlightDto> flights;
}
