package com.amu.quizplatform.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.amu.quizplatform.dto.AttemptAnswerDTO;
import com.amu.quizplatform.dto.AttemptDetailsDTO;
import com.amu.quizplatform.dto.AttemptResultDTO;
import com.amu.quizplatform.dto.AttemptSummaryDTO;
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

    private final UserRepository userRepository;
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final AttemptRepository attemptRepository;

    @Override
    public AttemptResultDTO submitQuiz(SubmitQuizRequestDTO request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        Quiz quiz = quizRepository.findById(request.getQuizId())
                .orElseThrow(() -> new RuntimeException("Quiz not found"));
        Attempt attempt = new Attempt();

        attempt.setUser(user);
        attempt.setQuiz(quiz);
        attempt.setTimeSpent(request.getTimeSpent());
        attempt.setAttemptedAt(LocalDateTime.now());

        int score = 0;
        Set<AttemptAnswers> attemptAnswers = new HashSet<>();
        for (Map.Entry<Long, Character> entry : request.getAnswers().entrySet()) {
            Question question = questionRepository.findById(entry.getKey())
                    .orElseThrow(() -> new RuntimeException("Question not found"));
            if (!quiz.getQuestions().contains(question)) {
                throw new RuntimeException("Question doesn't belong to this quiz");
            }
            if (question.getCorrect_answer().equalsIgnoreCase(String.valueOf(entry.getValue())))
                ;
            score++;

            AttemptAnswers attemptAnswer = new AttemptAnswers();
            attemptAnswer.setAttempt(attempt);
            attemptAnswer.setQuestion(question);
            attemptAnswer.setUserAnswer(entry.getValue());
            attemptAnswers.add(attemptAnswer);
        }
        attempt.setAnswers(attemptAnswers);
        attempt.setScore(score);

        attemptRepository.save(attempt);
        return new AttemptResultDTO(
                score,
                quiz.getQuestions().size(),
                attempt.getTimeSpent());
    }

    @Override
    public List<AttemptSummaryDTO> getMyAttempts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        List<Attempt> attemptList = attemptRepository.findByUser(user);
        List<AttemptSummaryDTO> result = new ArrayList<>();
        for (Attempt attempt : attemptList) {

            AttemptSummaryDTO dto = new AttemptSummaryDTO();
            dto.setAttemptId(attempt.getId());
            dto.setQuizTitle(attempt.getQuiz().getTitle());
            dto.setScore(attempt.getScore());
            dto.setTimeSpent(attempt.getTimeSpent());
            dto.setTotalQuestion(attempt.getQuiz().getQuestions().size());
            dto.setAttemptedAt(attempt.getAttemptedAt());

            result.add(dto);
        }
        return result;
    }

    @Override
    public AttemptDetailsDTO getAttempt(Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Attempt attempt = attemptRepository.findById(id).orElseThrow(() -> new RuntimeException("Attempt not found"));
        if (!attempt.getUser().getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized");
        }
        AttemptDetailsDTO Attemptdto = new AttemptDetailsDTO();
        Attemptdto.setAttemptId(attempt.getId());
        Attemptdto.setQuizTitle(attempt.getQuiz().getTitle());
        Attemptdto.setScore(attempt.getScore());
        Attemptdto.setTimeSpent(attempt.getTimeSpent());
        Attemptdto.setTotalQuestion(attempt.getQuiz().getQuestions().size());
        Attemptdto.setAttemptedAt(attempt.getAttemptedAt());
        List<AttemptAnswerDTO> answerDTOs = new ArrayList<>();
        for (AttemptAnswers answer : attempt.getAnswers()) {
            AttemptAnswerDTO dto = new AttemptAnswerDTO();
            dto.setQuestionId(answer.getQuestion().getId());
            dto.setQuestion(answer.getQuestion().getQuestion());
            dto.setOptionA(answer.getQuestion().getOptiona());
            dto.setOptionB(answer.getQuestion().getOptionb());
            dto.setOptionC(answer.getQuestion().getOptionc());
            dto.setOptionD(answer.getQuestion().getOptiond());
            dto.setUserAnswer(answer.getUserAnswer());
            dto.setCorrectAnswer(answer.getQuestion().getCorrect_answer());
            dto.setIsCorrect(
                    String.valueOf(answer.getUserAnswer())
                            .equalsIgnoreCase(answer.getQuestion().getCorrect_answer()));

            answerDTOs.add(dto);
        }
        Attemptdto.setAnswers(answerDTOs);
        return Attemptdto;
    }
}
