package com.amu.quizplatform.dto;

import lombok.Data;

@Data
public class QuestionRequest {
    
    private String questiontext;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String corrrectAnswer;
}
