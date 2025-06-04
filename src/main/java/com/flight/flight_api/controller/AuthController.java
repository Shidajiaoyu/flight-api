package com.flight.flight_api.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flight.flight_api.dto.RegisterRequestDto;
import com.flight.flight_api.dto.RegisterResponseDto;
import com.flight.flight_api.dto.UserDto;
import com.flight.flight_api.service.AuthService;

import jakarta.validation.Valid;

// 用户注册/登录
@RestController
@Validated
@RequestMapping("api/auth")
public class AuthController {

    // log出力
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    // 构造体
    // 注入AuthService
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // 用户登录
    @PostMapping("/login")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        // infoLog
        logger.info("Get users request initiated");
        List<UserDto> users = new ArrayList<>();
        // List<UserDto> users = authService.getAllUsers();

        // infoLog
        logger.info("Get users request completed");
        return ResponseEntity.ok(users);
    }

    // 用户注册
    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> createUsers(@RequestBody @Valid RegisterRequestDto in) {
        // infoLog
        logger.info("Create user request initiated");
        RegisterResponseDto out = authService.createUser(in.getUser());

        // infoLog
        logger.info("Create user request completed");
        return ResponseEntity.status(201).body(out);
    }
}
