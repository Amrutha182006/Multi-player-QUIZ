package com.amu.quizplatform.service;

import org.springframework.stereotype.Service;

import com.amu.quizplatform.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService 
{
    private final QuestionRepository questionRepository;
}
