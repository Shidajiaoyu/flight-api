package com.flight.flight_api.dto;

import java.util.List;

import lombok.Data;

// 认证状态响应Dto
@Data
public class AuthResponse {

    // 是否已认证
    private boolean authenticated;

    // 用户名（认证时才有值）
    String username;

    // 权限列表（认证时才有值）
    private List<String> authorities;
}
