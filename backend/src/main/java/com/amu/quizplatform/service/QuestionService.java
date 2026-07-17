package com.amu.quizplatform.service;

import java.util.List;

import com.amu.quizplatform.entity.Question;

public interface QuestionService {
    
    Question addQuestion(Question question);
    List<Question> getallQuestions();
    Question getQuestionbyId(Long id);
    void deleteQuestionbyId(Long id);
    Question updateQuestion(Long id,Question question);

}
