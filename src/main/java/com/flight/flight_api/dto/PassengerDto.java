package com.flight.flight_api.dto;

import lombok.Data;

// 乘客信息
@Data
public class PassengerDto {

    // 乘客姓名
    private String firstName;

    // 乘客姓名
    private String lastName;

    // 邮箱
    private String email;
}
