package com.amu.quizplatform.service;

import com.amu.quizplatform.dto.CreateRoomRequestDTO;
import com.amu.quizplatform.dto.RoomDTO;

public interface RoomService {

    RoomDTO createRoom(CreateRoomRequestDTO request);
} 
