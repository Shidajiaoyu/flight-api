package com.flight.flight_api.dto;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import lombok.Data;

// 订单信息
@Data
public class BookingDto {

    // 订单号
    private String bookingId;

    // 参考号
    private String reference;

    // 订单状态
    private String status;

    // 完成时间
    private Timestamp completeTime;

    // 出发城市
    private String departureCity;

    // 到达城市
    private String arrivalCity;

    // 出发日期
    private Date departureDate;

    // 出发时刻
    private Time departureTime;
}
