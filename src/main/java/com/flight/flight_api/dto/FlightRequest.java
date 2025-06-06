package com.flight.flight_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

// 航班检索请求Dto
@Data
public class FlightRequest {

    // 单程/往返
    @NotNull
    @Pattern(regexp = "[0-1]", message = "Invalid trip type")
    private String tripType;

    // 仓位
    @NotNull
    @Pattern(regexp = "[0-2]", message = "Invalid cabin")
    private String cabin;

    // 出发地
    @NotBlank(message = "Departure cannot be empty")
    private String departure;

    // 到达地
    private String arrival;

    // 出发时间
    private String departDate;

    // 到达时间
    private String returnDate;

    // 乘客人数
    private int passengers;
}
