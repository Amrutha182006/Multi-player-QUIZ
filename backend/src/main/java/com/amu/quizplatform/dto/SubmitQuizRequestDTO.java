package com.amu.quizplatform.dto;

import java.util.Map;

import lombok.Data;

@Data
public class SubmitQuizRequestDTO {

    private Long quizId;
    private Integer timeSpent;
    private Map<Long,Character> answers;
}
