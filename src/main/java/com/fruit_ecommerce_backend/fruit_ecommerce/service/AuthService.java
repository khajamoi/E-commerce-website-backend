package com.fruit_ecommerce_backend.fruit_ecommerce.service;

import com.fruit_ecommerce_backend.fruit_ecommerce.dto.LoginRequest;
import com.fruit_ecommerce_backend.fruit_ecommerce.dto.SignupRequest;
import com.fruit_ecommerce_backend.fruit_ecommerce.dto.JwtResponse;
import com.fruit_ecommerce_backend.fruit_ecommerce.entity.Role;
import com.fruit_ecommerce_backend.fruit_ecommerce.entity.User;
import com.fruit_ecommerce_backend.fruit_ecommerce.repository.UserRepository;
import com.fruit_ecommerce_backend.fruit_ecommerce.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public JwtResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

        return new JwtResponse(token, user.getEmail(), user.getRole().name());
    }

    public JwtResponse signup(SignupRequest request, Role role) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email is already taken");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role != null ? role : Role.USER) // ✅ store requested role or default USER
                .build();

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

        return new JwtResponse(token, user.getEmail(), user.getRole().name());
    }


}
