package com.example.flight_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.flight_api.dto.AuthenticationRequestDto;
import com.example.flight_api.dto.AuthenticationResponseDto;
import com.example.flight_api.dto.RegisterUserInfoDto;
import com.example.flight_api.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	@Autowired
    private final AuthenticationService authenticationService;

	/**
	 * process login authorization.
	 * 
	 * @param request loginInfo
	 * @return token info
	 */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> login(
            @RequestBody AuthenticationRequestDto request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
    
	/**
	 * process user's register.
	 * 
	 * @param registerUserInfo user's register information
	 * @return token info
	 */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDto> register(
            @RequestBody RegisterUserInfoDto registerUserInfo
    ) {
        return ResponseEntity.ok(authenticationService.register(registerUserInfo));
    }
}