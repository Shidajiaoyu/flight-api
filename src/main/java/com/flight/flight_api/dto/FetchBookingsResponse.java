package com.flight.flight_api.dto;

import java.util.List;

import lombok.Data;

// 订单查询响应Dto
@Data
public class FetchBookingsResponse {

    // 订单信息
    List<BookingDto> bookings;
}
