package com.flight.flight_api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// 异常响应
@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {

    // 状态码
    private int status;

    // 异常信息
    private String message;
}
