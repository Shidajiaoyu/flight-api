package com.flight.flight_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

// 用户登录请求Dto
@Data
public class LoginRequest {

    // 邮箱
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    // 密码
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, max = 20, message = "Password must be 8-20 characters")
    @Pattern(regexp = "^[\\w~!@#$%^&*]+$", message = "Password must contain uppercase, lowercase, numbers and symbols")
    private String password;
}
