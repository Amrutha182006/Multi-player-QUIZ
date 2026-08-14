package com.amu.quizplatform.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    private final AttemptService attemptService;

    @PostMapping("/submit")
    public AttemptResultDTO submitQuiz(@RequestBody SubmitQuizRequestDTO request){
        return attemptService.submitQuiz(request);
    }

    @GetMapping("/my-attempts")
    public List<AttemptSummaryDTO> getMyAttempts(){
        return attemptService.getMyAttempts();
    }

    @GetMapping("/{id}")
    public AttemptDetailsDTO getAttempt(@PathVariable Long id)
    {
        return attemptService.getAttempt(id);
    }
    @GetMapping("/{quizId}")
    public List<LeaderboardDTO> getLeaderboard(Long quizId)
    {
        return attemptService.getLeaderboard(quizId);
    }

}
