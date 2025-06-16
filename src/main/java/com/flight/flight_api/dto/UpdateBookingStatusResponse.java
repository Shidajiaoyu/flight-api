package com.flight.flight_api.dto;

import java.util.List;

import lombok.Data;

// 更新订单状态
@Data
public class UpdateBookingStatusResponse {

    // 订单号
    String bookingId;

    // 往返订单信息
    List<BookingDto> bookings;
}
