package com.amu.quizplatform.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amu.quizplatform.entity.Question;
import com.amu.quizplatform.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService 
{
    private final QuestionRepository questionRepository;

    @Override
    public Question addQuestion(Question question)
    {
        return questionRepository.save(question);
    }
    @Override
    public List<Question> getallQuestions()
    {
        return questionRepository.findAll();
    }
    @Override
    public Question getQuestionbyId(Long id)
    {
        return questionRepository.findById(id).orElseThrow();
    }
    @Override
    public void deleteQuestionbyId(Long id)
    {
        questionRepository.deleteById(id);
    }
    @Override
    public Question updateQuestion(Long id,Question question)
    {
        Question existingQuestion=questionRepository.findById(id).orElseThrow();

        existingQuestion.setQuestion(question.getQuestion());
        existingQuestion.setOptiona(question.getOptiona());
        existingQuestion.setOptionb(question.getOptionb());
        existingQuestion.setOptionc(question.getOptionc());
        existingQuestion.setOptiond(question.getOptiond());
        existingQuestion.setCorrect_answer(question.getCorrect_answer());

        return questionRepository.save(existingQuestion);
    }

} 
