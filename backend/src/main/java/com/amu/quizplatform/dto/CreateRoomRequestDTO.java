package com.amu.quizplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoomRequestDTO {

    private Long quizId;
    private Integer maxPlayers;
    
}
