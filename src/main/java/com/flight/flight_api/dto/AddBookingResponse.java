package com.flight.flight_api.dto;

import java.math.BigDecimal;

import lombok.Data;

// 订单登录响应Dto
@Data
public class AddBookingResponse {

    // 订单号
    private String bookingId;

    // 总价格(不含税)
    private BigDecimal totalPrice;

    // 税等其他费用总额
    private BigDecimal otherFee;
}
