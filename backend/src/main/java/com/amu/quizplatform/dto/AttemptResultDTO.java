package com.amu.quizplatform.dto;

import lombok.Data;

@Data
public class AttemptResultDTO {
    
    private Integer score;
    private Integer totalQuestion;
    private Integer timeSpent;
}
