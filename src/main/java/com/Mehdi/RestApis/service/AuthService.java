package com.Mehdi.RestApis.service;

import com.Mehdi.RestApis.dto.AuthResponse;
import com.Mehdi.RestApis.dto.LoginRequest;
import com.Mehdi.RestApis.dto.RegisterRequest;
import com.Mehdi.RestApis.dto.UserDto;
import com.Mehdi.RestApis.model.User;
import com.Mehdi.RestApis.repo.UserRepository;
import com.Mehdi.RestApis.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Transactional
    public AuthResponse register(RegisterRequest req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }
        User u = new User();
        u.setEmail(req.getEmail());
        u.setPasswordHash(passwordEncoder.encode(req.getPassword()));
        u.setDisplayName(req.getDisplayName());
        userRepository.save(u);

        String token = jwtUtil.generateToken(u.getId(), u.getEmail());
        UserDto userDto = new UserDto(u.getId(), u.getEmail(), u.getDisplayName());
        return new AuthResponse(token, userDto);
    }

    public AuthResponse login(LoginRequest req) {
        Optional<User> userOpt = userRepository.findByEmail(req.getEmail());
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("Invalid credentials");
        }
        User u = userOpt.get();
        if (!passwordEncoder.matches(req.getPassword(), u.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid credentials");
        }
        String token = jwtUtil.generateToken(u.getId(), u.getEmail());
        UserDto userDto = new UserDto(u.getId(), u.getEmail(), u.getDisplayName());
        return new AuthResponse(token, userDto);
    }
}