package com.amu.quizplatform.dto;

import lombok.Data;

@Data
public class QuestionResponseDTO {

    private Long id;

    private String question;

    private String optionA;

    private String optionB;

    private String optionC;

    private String optionD;
    
}
