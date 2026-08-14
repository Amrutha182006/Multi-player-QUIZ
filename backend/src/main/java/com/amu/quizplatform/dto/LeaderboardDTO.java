package com.amu.quizplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaderboardDTO {

    private Integer rank;

    private String username;

    private Integer score;

    private Integer timeSpent;

}
