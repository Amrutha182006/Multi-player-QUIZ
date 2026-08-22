package com.amu.quizplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomEventDTO {

    private String type;
    private String roomCode;
    private String username;
    private Integer currentPlayers;
}