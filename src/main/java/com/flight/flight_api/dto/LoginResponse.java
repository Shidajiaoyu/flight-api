package com.flight.flight_api.dto;

import lombok.Data;

// 用户登录响应Dto
@Data
public class LoginResponse {

    // 用户ID
    private Long userId;

    // 邮箱
    private String email;

    // 密码
    private String password;

    // token
    String token;
}
