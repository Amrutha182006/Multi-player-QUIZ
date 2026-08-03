package com.amu.quizplatform.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttemptSummaryDTO 
{
    private Long attemptId;
    private String quizTitle;
    private Integer score;
    private Integer totalQuestion;
    private Integer timeSpent;
    private LocalDateTime attemptedAt;    
}
