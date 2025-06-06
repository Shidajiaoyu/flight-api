package com.flight.flight_api.dto;

import java.util.List;

import lombok.Data;

// 航班检索响应Dto
@Data
public class FlightResponse {

    // 航班信息
    List<FlightDto> flights;
}
