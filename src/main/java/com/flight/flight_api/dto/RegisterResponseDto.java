package com.flight.flight_api.dto;

import lombok.Data;

// 用户注册响应Dto
@Data
public class RegisterResponseDto {
    // 用户信息
    UserDto user;

    // token
    String token;

}
