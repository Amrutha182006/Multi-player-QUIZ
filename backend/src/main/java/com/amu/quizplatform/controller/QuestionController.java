package com.amu.quizplatform.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amu.quizplatform.dto.QuestionRequest;
import com.amu.quizplatform.service.QuestionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

  private final QuestionService questionService;    

  @PostMapping
  public ResponseEntity<String> addQuestion(@RequestBody QuestionRequest request){
    return ResponseEntity.ok("Received");
  }
}
