package com.flight.flight_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

// 用户信息
@Data
public class UserDto {

    // 用户ID
    private Long userId;

    // 邮箱
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    // 密码
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, max = 20, message = "Password must be 8-20 characters")
    @Pattern(regexp = "^[\\w~!@#$%^&*]+$", message = "Password must contain uppercase, lowercase, numbers and symbols")
    private String password;

    // 用户名
    @NotBlank(message = "First name cannot be empty")
    @Size(max = 50, message = "First name must be 1-50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "First name can only contain letters or digits")
    private String firstName;

    // 用户名
    @NotBlank(message = "Last name cannot be empty")
    @Size(max = 50, message = "Last name must be 1-50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Last name can only contain letters or digits")
    private String lastName;

    // 国家
    @NotBlank(message = "country cannot be empty")
    private String country;

    // 手机号
    // @Nullable
    // @Size(min = 11, max = 11, message = "Phone number can only contain digits")
    // @Pattern(regexp = "^\\d$", message = "Must be 12-digit number")
    private String phone;
}
