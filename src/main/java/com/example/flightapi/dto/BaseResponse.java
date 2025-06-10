package com.example.flightapi.dto;

import lombok.Data;

@Data
public class BaseResponse<T> {
    private boolean success;
    private int code;
    private String message;
    private T data;

    public static <T> BaseResponse<T> success(T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setSuccess(true);
        response.setCode(200);
        response.setMessage("OK");
        response.setData(data);
        return response;
    }

    public static <T> BaseResponse<T> error(int code, String message) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setSuccess(false);
        response.setCode(code);
        response.setMessage(message);
        response.setData(null);
        return response;
    }
}
