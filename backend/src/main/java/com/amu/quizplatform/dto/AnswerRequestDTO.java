package com.amu.quizplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerRequestDTO {
    
    Long questionId;
    String userAnswer;
}
