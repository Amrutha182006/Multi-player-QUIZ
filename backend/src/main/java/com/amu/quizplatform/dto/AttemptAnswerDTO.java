package com.amu.quizplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttemptAnswerDTO {

    private Long questionId;
    private String question;

    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    private Character userAnswer;
    private String correctAnswer;

    private Boolean isCorrect;
    
}
