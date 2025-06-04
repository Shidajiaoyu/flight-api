package com.flight.flight_api.exception;

// 异常处理
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final int code;

    public ServiceException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
