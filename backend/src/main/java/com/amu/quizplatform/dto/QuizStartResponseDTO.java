package com.amu.quizplatform.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizStartResponseDTO {

    private Long quizId;

    private String title;

    private String description;

    private String difficulty;

    private Integer timeLimit;

    private List<QuestionResponseDTO> questions;
    
}
