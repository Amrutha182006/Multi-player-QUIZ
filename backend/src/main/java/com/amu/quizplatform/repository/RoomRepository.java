package com.amu.quizplatform.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amu.quizplatform.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> 
{
    Optional<Room> findByRoomCode(String roomCode);
}
