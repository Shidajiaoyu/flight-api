package com.flight.flight_api.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flight.flight_api.config.JwtUtil;
import com.flight.flight_api.dto.RegisterResponseDto;
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
    // 注入userRepository
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
    private RegisterResponseDto convertToRegisterResponse(UserEntity user) {

        if (null == user) {
            return null;
        }

        UserDto userDto = userMapper.toUserDto(user);
        String token = jwtUtil.generateToken(user.getEmail());

        RegisterResponseDto res = new RegisterResponseDto();
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

    // 获取所有的用户信息
    // public List<UserDto> getAllUsers() {
    // return
    // userRepository.findAll().stream().map(this::mapper.toUserDto).collect(Collectors.toList());
    // }

    // 获取指定UserId的用户信息
    public boolean getUserByEmail(String email) {

        return userRepository.existsByEmail(email);
    }

    // 获取指定UserId的用户信息
    public UserDto getUserByUserId(Long userId) {

        UserEntity user = userRepository.findByUserId(userId);
        return userMapper.toUserDto(user);
    }

    // 用户注册信息登录
    @Transactional
    public RegisterResponseDto createUser(UserDto userIn) {

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

}
