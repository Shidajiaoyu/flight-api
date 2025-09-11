package com.example.flight_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.flight_api.entity.User;
import com.example.flight_api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
	private final UserRepository userRepository;

    public User getUserInfo(String usrname){
        return userRepository.findByUsername(usrname);
    }

}
