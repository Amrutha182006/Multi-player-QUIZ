package com.amu.quizplatform.service;

import com.amu.quizplatform.dto.AttemptResultDTO;
import com.amu.quizplatform.dto.SubmitQuizRequestDTO;

public interface AttemptService {
     AttemptResultDTO submitQuiz(SubmitQuizRequestDTO request);    
}
