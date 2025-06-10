package com.example.flightapi.service;

import com.example.flightapi.dto.*;
import com.example.flightapi.entity.User;
import com.example.flightapi.repository.UserRepository;
import com.example.flightapi.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDTO login(LoginRequestDTO loginDTO) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );
        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        String token = jwtUtil.generateToken(user.getEmail());

        UserInfoDTO userInfo = new UserInfoDTO();
        userInfo.setId(user.getId());
        userInfo.setEmail(user.getEmail());
        userInfo.setFirstName(user.getFirstName());

        LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(token);
        response.setUser(userInfo);

        return response;
    }

    public LoginResponseDTO register(RegisterDTO registerDTO) {
        if (userRepository.existsByEmail(registerDTO.getEmail())) {
            throw new RuntimeException("Email already registered");
        }
        User user = User.builder()
                .email(registerDTO.getEmail())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .firstName(registerDTO.getFirstName())
                .lastName(registerDTO.getLastName())
                .country(registerDTO.getCountry())
                .phone(registerDTO.getPhone())
                .build();
        userRepository.save(user);

        LoginRequestDTO loginReq = new LoginRequestDTO();
        loginReq.setEmail(registerDTO.getEmail());
        loginReq.setPassword(registerDTO.getPassword());
        return login(loginReq);
    }
}
