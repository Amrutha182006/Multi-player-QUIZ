package com.amu.quizplatform.service;

import java.util.List;

import com.amu.quizplatform.entity.Quiz;

public interface QuizService 
{
    Quiz addQuiz(Quiz quiz);
    List<Quiz> getallQuizs();
    Quiz getQuizbyId(Long id);
    void deleteQuizbyId(Long id);
}
