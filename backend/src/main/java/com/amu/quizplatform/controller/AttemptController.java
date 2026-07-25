package com.amu.quizplatform.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amu.quizplatform.dto.*;
import com.amu.quizplatform.service.AttemptService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/attempt")
@RequiredArgsConstructor
public class AttemptController {

    private AttemptService attemptService;

    @PostMapping("/submit")
    private AttemptResultDTO submitQuiz(@RequestBody SubmitQuizRequestDTO request){
        return attemptService.submitQuiz(request);
    }

}
