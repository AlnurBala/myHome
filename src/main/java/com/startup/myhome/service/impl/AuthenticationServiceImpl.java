package com.startup.myhome.service.impl;

import com.startup.myhome.dto.request.AuthenticationRequest;
import com.startup.myhome.dto.request.RegisterRequest;
import com.startup.myhome.dto.response.AuthenticationResponseDto;
import com.startup.myhome.entity.Users;
import com.startup.myhome.enumeration.Role;
import com.startup.myhome.repository.UsersRepository;
import com.startup.myhome.service.impl.JwtServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl {

    private static final Logger logger
            = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    private final UsersRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtServiceImpl jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseDto register(RegisterRequest request) {
        logger.info("Registering user: {}", request.getEmail());
        var user = Users.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .monthlyLimit(request.getMonthlyLimit())
                .currentMonthlyLimit(request.getMonthlyLimit())
                .role(request.getRoles().isEmpty() ? Role.USER : request.getRoles().iterator().next())
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        logger.info("User registered successfully: {}", request.getEmail());
        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponseDto authenticate(AuthenticationRequest request) {
        logger.info("Authenticating user: {}", request.getEmail());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        logger.info("User authenticated successfully: {}", request.getEmail());
        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .build();
    }
}
