package com.amu.quizplatform.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amu.quizplatform.dto.CreateRoomRequestDTO;
import com.amu.quizplatform.dto.RoomDTO;
import com.amu.quizplatform.entity.Room;
import com.amu.quizplatform.service.RoomService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {
    
    private final RoomService roomService;
    @PostMapping
    public RoomDTO createRoom(@RequestBody CreateRoomRequestDTO request){

        return roomService.createRoom(request);
    }
    @GetMapping("/{roomCode}")
    public Room showRoom(@PathVariable String roomCode)
    {
        return roomService.showRoom(roomCode);
    }
    @PostMapping("/{roomCode}/join")
    public RoomDTO joinRoom(@PathVariable String roomCode)
    {
        return roomService.joinRoom(roomCode);
    }
    @PostMapping("/{roomCode}/start")
    public void startRoom(@PathVariable String roomCode)
    {
        roomService.startRoom(roomCode);
    }
}
