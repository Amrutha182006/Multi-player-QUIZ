package com.amu.quizplatform.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.amu.quizplatform.dto.AddQuestionsRequest;
import com.amu.quizplatform.dto.QuestionResponseDTO;
import com.amu.quizplatform.dto.QuizStartResponseDTO;
import com.amu.quizplatform.entity.Question;
import com.amu.quizplatform.entity.Quiz;
import com.amu.quizplatform.repository.QuestionRepository;
import com.amu.quizplatform.repository.QuizRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

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

    @Override
    public Quiz addQuestionsToQuiz(Long quizId, AddQuestionsRequest request) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow();
        List<Question> questions = questionRepository.findAllById(request.getQuestionIds());
        if (questions.size() != request.getQuestionIds().size()) {
            throw new RuntimeException("One or more questions not found");
        }
        quiz.getQuestions().addAll(questions);
        return quizRepository.save(quiz);
    }

    @Override
    public QuizStartResponseDTO startQuiz(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow();
        List<QuestionResponseDTO> responseQuestion = new ArrayList<>();
        for (Question question : quiz.getQuestions()) {
            QuestionResponseDTO dto = new QuestionResponseDTO();

            dto.setId(question.getId());
            dto.setQuestion(question.getQuestion());
            dto.setOptionA(question.getOptiona());
            dto.setOptionB(question.getOptionb());
            dto.setOptionC(question.getOptionc());
            dto.setOptionD(question.getOptiond());

            responseQuestion.add(dto);
        }
        QuizStartResponseDTO response = new QuizStartResponseDTO();
        response.setQuizId(quiz.getId());
        response.setTitle(quiz.getTitle());
        response.setDescription(quiz.getDescription());
        response.setDifficulty(quiz.getDifficulty());
        response.setTimeLimit(quiz.getTimeLimit());
        response.setQuestions(responseQuestion);

        return response;
    }
}
