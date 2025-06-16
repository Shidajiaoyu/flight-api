package com.flight.flight_api.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.flight.flight_api.entity.UserEntity;
import com.flight.flight_api.repository.UserRepository;

@Primary
@Service
public class CustomUserDetailService implements UserDetailsService {

    // 用户表操作
    private final UserRepository userRepository;

    // 构造体
    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                new ArrayList<>()) {
        };
    }
}
