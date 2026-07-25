package com.amu.quizplatform.dto;

import java.util.List;

import lombok.Data;

@Data
public class AddQuestionsRequest {

    private List<Long> questionIds;
    
}
