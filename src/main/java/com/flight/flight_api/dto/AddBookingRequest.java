package com.flight.flight_api.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

// 订单登录请求Dto
@Data
public class AddBookingRequest {

    // 用户ID
    @NotNull(message = "User ID cannot be empty")
    private Long userId;

    // 出发航班Id
    @NotNull(message = "No departure flight information is provided")
    private Long departureFlightId;

    // 返程航班Id
    private Long retrunFlightId;

    // 乘客信息
    @Size(min = 1, message = "No passenger information is provided")
    private List<PassengerDto> passengers;
}
