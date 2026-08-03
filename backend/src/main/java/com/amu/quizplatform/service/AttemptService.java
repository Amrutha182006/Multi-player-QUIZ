package com.amu.quizplatform.service;

import java.util.List;

import com.amu.quizplatform.dto.AttemptResultDTO;
import com.amu.quizplatform.dto.AttemptSummaryDTO;
import com.amu.quizplatform.dto.SubmitQuizRequestDTO;

public interface AttemptService {
     AttemptResultDTO submitQuiz(SubmitQuizRequestDTO request);   
     List<AttemptSummaryDTO> getMyAttempts();
}
