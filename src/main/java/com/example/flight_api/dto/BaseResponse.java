package com.example.flight_api.dto;

import com.example.flight_api.enums.HttpStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Unified Response Set Processor
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {

    private Boolean success;

    private Integer code;

    private String message;

    private T data;

    private static <T> BaseResponse<T> response(Boolean success, Integer code, String message, T data) {
        BaseResponse<T> BaseResponse = new BaseResponse<>();
        BaseResponse.setSuccess(success);
        BaseResponse.setCode(code);
        BaseResponse.setMessage(message);
        BaseResponse.setData(data);
        return BaseResponse;
    }

    private static <T> BaseResponse<T> response(Boolean success, Integer code, String message) {
        BaseResponse<T> BaseResponse = new BaseResponse<>();
        BaseResponse.setSuccess(success);
        BaseResponse.setCode(code);
        BaseResponse.setMessage(message);
        return BaseResponse;
    }

    public static <T> BaseResponse<T> success() {
        return response(true, HttpStatusEnum.SUCCESS.getCode(), HttpStatusEnum.SUCCESS.getMessage(), null);
    }

    public static <T> BaseResponse<T> success(HttpStatusEnum httpResponseEnum) {
        return response(true, httpResponseEnum.getCode(), httpResponseEnum.getMessage());
    }

    public static <T> BaseResponse<T> success(Integer code, String message) {
        return response(true, code, message);
    }

    public static <T> BaseResponse<T> success(String message, T data) {
        return response(true, HttpStatusEnum.SUCCESS.getCode(), message, data);
    }

    public static <T> BaseResponse<T> success(Integer code, String message, T data) {
        return response(true, code, message, data);
    }

    public static <T> BaseResponse<T> success(T data) {
        return response(true, HttpStatusEnum.SUCCESS.getCode(), HttpStatusEnum.SUCCESS.getMessage(), data);
    }

    public static <T> BaseResponse<T> success(String message) {
        return response(true, HttpStatusEnum.SUCCESS.getCode(), message, null);
    }

    public static <T> BaseResponse<T> fail() {
        return response(false, HttpStatusEnum.ERROR.getCode(), HttpStatusEnum.ERROR.getMessage(), null);
    }

    public static <T> BaseResponse<T> fail(HttpStatusEnum httpResponseEnum) {
        return response(false, httpResponseEnum.getCode(), httpResponseEnum.getMessage());
    }

    public static <T> BaseResponse<T> fail(Integer code, String message) {
        return response(false, code, message);
    }

    public static <T> BaseResponse<T> fail(String message, T data) {
        return response(false, HttpStatusEnum.ERROR.getCode(), message, data);
    }

    public static <T> BaseResponse<T> fail(Integer code, String message, T data) {
        return response(false, code, message, data);
    }

    public static <T> BaseResponse<T> fail(T data) {
        return response(false, HttpStatusEnum.ERROR.getCode(), HttpStatusEnum.ERROR.getMessage(), data);
    }

    public static <T> BaseResponse<T> fail(String message) {
        return response(false, HttpStatusEnum.ERROR.getCode(), message, null);
    }

}
