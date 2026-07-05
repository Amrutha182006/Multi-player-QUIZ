package com.amu.quizplatform.service;

public interface JWTService {

    String generateToken(String username);
    String extractUsername(String token);
    boolean isTokenValid(String token);
    
}
