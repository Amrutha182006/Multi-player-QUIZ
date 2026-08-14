package com.amu.quizplatform.dto;

import com.amu.quizplatform.entity.RoomStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {

    private Long id;
    private String roomCode;
    private String quizTitle;
    private String hostUsername;
    private RoomStatus status;
    private Integer maxPlayers;
    private Integer currentPlayers;

}
