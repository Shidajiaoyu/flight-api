package com.example.flight_api.enums;

import lombok.Getter;

@Getter
public enum HttpStatusEnum {

    SUCCESS(200, "OK"),
    CREATED(201, "Created"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    NOT_FOUND(404, "Not Found"),
    ERROR(500, "Internal Server Error");

    private final Integer code;

    private final String message; 

    HttpStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
