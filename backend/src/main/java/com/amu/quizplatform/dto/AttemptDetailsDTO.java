package com.amu.quizplatform.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttemptDetailsDTO {

    private Long attemptId;
    private String quizTitle;
    private Integer score;
    private Integer totalQuestion;
    private Integer timeSpent;
    private LocalDateTime attemptedAt;

    private List<AttemptAnswerDTO> answers;

    
}
