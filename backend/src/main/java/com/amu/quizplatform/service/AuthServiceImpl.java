package com.amu.quizplatform.service;

import com.amu.quizplatform.dto.RegisterRequest;
import com.amu.quizplatform.entity.User;
import com.amu.quizplatform.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordencoder;

    @Override
    public void register(RegisterRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordencoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);
    }

}