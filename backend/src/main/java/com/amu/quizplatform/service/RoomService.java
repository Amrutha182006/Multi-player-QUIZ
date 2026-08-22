package com.amu.quizplatform.service;

import com.amu.quizplatform.dto.CreateRoomRequestDTO;
import com.amu.quizplatform.dto.RoomDTO;
import com.amu.quizplatform.entity.Room;

public interface RoomService {

    RoomDTO createRoom(CreateRoomRequestDTO request);
    RoomDTO joinRoom(String roomCode);
    Room showRoom(String roomCode);
    RoomDTO startRoom(String roomCode);
} 
