package com.amu.quizplatform.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amu.quizplatform.entity.Quiz;
import com.amu.quizplatform.repository.QuizRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizServiceIml implements QuizService {

    private final QuizRepository quizRepository ;

    @Override
    public Quiz addQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public List<Quiz> getallQuizs() {
        return quizRepository.findAll();
    }

    @Override
    public Quiz getQuizbyId(Long id) {
        return quizRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteQuizbyId(Long id) {
        quizRepository.deleteById(id);
    }
}
