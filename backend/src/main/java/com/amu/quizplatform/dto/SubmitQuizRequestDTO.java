package com.amu.quizplatform.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmitQuizRequestDTO {

    private Long quizId;
    private Integer timeSpent;
    private Map<Long,Character> answers;
}
