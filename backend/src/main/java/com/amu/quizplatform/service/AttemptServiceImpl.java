package com.amu.quizplatform.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.amu.quizplatform.dto.AttemptResultDTO;
import com.amu.quizplatform.dto.SubmitQuizRequestDTO;
import com.amu.quizplatform.entity.Attempt;
import com.amu.quizplatform.entity.AttemptAnswers;
import com.amu.quizplatform.entity.Question;
import com.amu.quizplatform.entity.Quiz;
import com.amu.quizplatform.entity.User;
import com.amu.quizplatform.repository.*;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttemptServiceImpl implements AttemptService {

    private UserRepository userRepository;
    private QuizRepository quizRepository;
    private QuestionRepository questionRepository;
    private AttemptRepository attemptRepository;
    @Override
    public AttemptResultDTO submitQuiz(SubmitQuizRequestDTO request)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        User user= userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("User not found"));
        Quiz quiz= quizRepository.findById(request.getQuizId()).orElseThrow(()->new RuntimeException("Quiz not found"));
        Attempt attempt = new Attempt();
        
        attempt.setUser(user);
        attempt.setQuiz(quiz);
        attempt.setTimeSpent(request.getTimeSpent());
        attempt.setAttemptedAt(LocalDateTime.now());

        int score =0;
        Set<AttemptAnswers> attemptAnswers=new HashSet<>();
        for(Map.Entry<Long, Character> entry: request.getAnswers().entrySet())
        {
             Question question=questionRepository.findById(entry.getKey()).orElseThrow(()->new RuntimeException("Question not found"));
             if(!quiz.getQuestions().contains(question)){
                throw new RuntimeException("Question doesn't belong to this quiz");
             }
             if(question.getCorrect_answer().equalsIgnoreCase(String.valueOf(entry.getValue())));
             score++;

             AttemptAnswers attemptAnswer=new AttemptAnswers();
             attemptAnswer.setAttempt(attempt);
             attemptAnswer.setQuestion(question);
             attemptAnswer.setUserAnswer(entry.getValue());
             attemptAnswers.add(attemptAnswer);
        }
        attempt.setAnswers(attemptAnswers);
        attempt.setScore(score);

        attemptRepository.save(attempt);
        return null;

    }
    
}
