package com.flight.flight_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flight.flight_api.config.JwtUtil;
import com.flight.flight_api.dto.AuthResponse;
import com.flight.flight_api.dto.LoginResponse;
import com.flight.flight_api.dto.RegisterResponse;
import com.flight.flight_api.dto.UserDto;
import com.flight.flight_api.dto.UserMapper;
import com.flight.flight_api.entity.UserEntity;
import com.flight.flight_api.exception.ServiceException;
import com.flight.flight_api.repository.UserRepository;

// 用户注册登录相关操作
@Service
public class AuthService {

    // 用户表操作
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;

    // 构造体
    // 注入UserRepository等
    public AuthService(UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil,
            UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.userMapper = userMapper;
    }

    // 把用户信息表里取到的数据转换成用户Dto
    private RegisterResponse convertToRegisterResponse(UserEntity user) {

        if (null == user) {
            return null;
        }

        UserDto userDto = userMapper.toUserDto(user);
        String token = jwtUtil.generateAccessToken(user.getEmail());

        RegisterResponse res = new RegisterResponse();
        res.setUser(userDto);
        res.setToken(token);

        return res;
    }

    // 把用户信息表里取到的数据转换成用户Dto
    private UserEntity convertToEntity(UserDto dto) {

        if (null == dto) {
            return null;
        }

        UserEntity user = userMapper.toUserEntity(dto);

        return user;
    }

    // 用户注册信息登录
    @Transactional
    public RegisterResponse createUser(UserDto userIn) {

        // 邮箱重复检查
        if (userRepository.existsByEmail(userIn.getEmail())) {
            throw new ServiceException(1000, "This email has already been registered");
        }

        // 密码加密后存储到DB
        String encodedPassword = passwordEncoder.encode(userIn.getPassword());
        userIn.setPassword(encodedPassword);
        UserEntity newUser = userRepository.save(convertToEntity(userIn));

        return convertToRegisterResponse(newUser);
    }

    // 验证用户是否已注册
    @Transactional
    public LoginResponse findUserByEmail(String email, String password) {

        Optional<UserEntity> user = userRepository.findByEmail(email);

        // 用户不存在
        if (!user.isPresent()) {
            throw new ServiceException(1002, "The email or password you entered are incorrect‌");
        }

        UserEntity entity = user.get();
        String encodedPassword = passwordEncoder.encode(password);

        if (passwordEncoder.matches(encodedPassword, entity.getPassword())) {
            throw new ServiceException(1003, "The password you entered is incorrect‌");
        }

        LoginResponse res = new LoginResponse();
        res.setUserId(entity.getUserId());
        res.setEmail(email);
        res.setPassword(encodedPassword);
        String token = jwtUtil.generateAccessToken(email);
        res.setToken(token);

        return res;
    }

    public AuthResponse checkAuthentication(Authentication auth) {

        AuthResponse authResponse = new AuthResponse();

        if (auth != null &&
                auth.isAuthenticated() &&
                !(auth instanceof AnonymousAuthenticationToken)) {
            authResponse.setAuthenticated(true);
            authResponse.setUsername(auth.getName());
            authResponse.setAuthorities(
                    auth.getAuthorities().stream()
                            .map(grantedAuthority -> grantedAuthority.getAuthority())
                            .toList());
        } else {
            authResponse.setAuthenticated(false);
            authResponse.setUsername(null);
            authResponse.setAuthorities(List.of());
        }

        return authResponse;
    }
}
