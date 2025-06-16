package com.flight.flight_api.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 异常响应
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    // 状态码
    private int status;

    // 异常信息
    private String message;

    // 异常详情
    private String details;
}
