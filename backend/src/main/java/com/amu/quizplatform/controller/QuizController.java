package com.amu.quizplatform.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amu.quizplatform.dto.AddQuestionsRequest;
import com.amu.quizplatform.dto.QuizStartResponseDTO;
import com.amu.quizplatform.entity.Quiz;
import com.amu.quizplatform.service.QuizService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @PostMapping
    public Quiz addQuiz(@RequestBody Quiz quiz) {
        return quizService.addQuiz(quiz);
    }

    @GetMapping
    public List<Quiz> getallQuizs() {
        return quizService.getallQuizs();
    }

    @GetMapping("/{id}")
    public Quiz getQuizbyId(@PathVariable Long id) {
        return quizService.getQuizbyId(id);
    }

    @DeleteMapping("/{id}")
    public void deleteQuizbyId(@PathVariable Long id) {
        quizService.deleteQuizbyId(id);
    }

    @PutMapping("/{quizId}/questions")
    public Quiz addQuestionsToQuiz(@PathVariable Long quizId, @RequestBody AddQuestionsRequest request) {
        return quizService.addQuestionsToQuiz(quizId, request);
    }

    @GetMapping("/{quizId}/start")
    public QuizStartResponseDTO startQuiz(@PathVariable Long quizId) {
        return quizService.startQuiz(quizId);
    }

}
