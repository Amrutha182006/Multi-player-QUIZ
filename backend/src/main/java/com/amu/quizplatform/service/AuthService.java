package com.amu.quizplatform.service;

import com.amu.quizplatform.dto.RegisterRequest;

public interface AuthService {

    void register(RegisterRequest request);

}