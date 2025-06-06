package com.example.flight_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.flight_api.dto.AuthenticationRequestDto;
import com.example.flight_api.dto.AuthenticationResponseDto;
import com.example.flight_api.dto.RegisterUserInfoDto;
import com.example.flight_api.entity.User;
import lombok.RequiredArgsConstructor;
import com.example.flight_api.repository.UserRepository;
import com.example.flight_api.util.JwtUtil;
import jakarta.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	@Autowired
	private final UserRepository userRepository;
	
	@Autowired
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	private final JwtUtil jwtUtil;
	
	@Autowired
	private final AuthenticationManager authenticationManager;

	public AuthenticationResponseDto authenticate(AuthenticationRequestDto request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		User user = userRepository.findByUsername(request.getUsername());

		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
				.password(user.getPasswordHash()).roles("USER").build();

		String jwtToken = jwtUtil.generateToken(userDetails);
		return AuthenticationResponseDto.builder().username(request.getUsername()).token(jwtToken).build();
	}

	@Transactional
	public AuthenticationResponseDto register(RegisterUserInfoDto registerUserInfo) {
		User user = User.builder().username(registerUserInfo.getUsername())
				.passwordHash(passwordEncoder.encode(registerUserInfo.getPassword())).email(registerUserInfo.getEmail())
				.firstName(registerUserInfo.getFirstName()).lastName(registerUserInfo.getLastName()).build();

		userRepository.save(user);

		UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
				.password(user.getPasswordHash()).roles("USER").build();

		String jwtToken = jwtUtil.generateToken(userDetails);
		return AuthenticationResponseDto.builder().token(jwtToken).build();
	}
}