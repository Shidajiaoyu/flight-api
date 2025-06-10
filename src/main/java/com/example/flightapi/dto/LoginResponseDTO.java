package com.example.flightapi.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String token;
    private UserInfoDTO user;
}
