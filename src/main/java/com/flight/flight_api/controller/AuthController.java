package com.flight.flight_api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flight.flight_api.dto.AuthResponse;
import com.flight.flight_api.dto.LoginRequest;
import com.flight.flight_api.dto.LoginResponse;
import com.flight.flight_api.dto.RegisterRequest;
import com.flight.flight_api.dto.RegisterResponse;
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
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest in) {
        // infoLog
        logger.info("Login request initiated");

        LoginResponse out = authService.findUserByEmail(in.getEmail(), in.getPassword());

        // infoLog
        logger.info("Login request completed");
        return ResponseEntity.ok(out);
    }

    // 用户注册
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest in) {
        // infoLog
        logger.info("Register request initiated");
        RegisterResponse out = authService.createUser(in.getUser());

        // infoLog
        logger.info("Register request completed");
        return ResponseEntity.status(HttpStatus.CREATED).body(out);
    }

    // 登录状态检测
    @GetMapping("/check")
    public ResponseEntity<AuthResponse> authCheck() {
        // infoLog
        logger.info("AuthCheck request initiated");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        AuthResponse authResponse = authService.checkAuthentication(auth);

        // infoLog
        logger.info("AuthCheck request completed");

        return ResponseEntity.ok(authResponse);
    }
}
