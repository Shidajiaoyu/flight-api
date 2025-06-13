package com.flight.flight_api.dto;

import jakarta.validation.Valid;
import lombok.Data;

// 用户注册请求Dto
@Data
public class RegisterRequest {
    // 用户信息
    @Valid
    UserDto user;
}
