package com.flight.flight_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

// 航班检索请求Dto
@Data
public class FlightRequest {

    // 仓位
    private String cabin = "0";

    // 出发地
    @NotBlank(message = "Departure cannot be empty")
    private String departure;

    // 到达地
    @NotBlank(message = "Arrival cannot be empty")
    private String arrival;

    // 出发时间
    private String departDate;

    // 乘客数
    private int passengers = 1;
}
