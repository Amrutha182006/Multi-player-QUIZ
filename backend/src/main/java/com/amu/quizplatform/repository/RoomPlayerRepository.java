package com.amu.quizplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amu.quizplatform.entity.Room;
import com.amu.quizplatform.entity.RoomPlayer;
import com.amu.quizplatform.entity.User;

public interface RoomPlayerRepository extends JpaRepository<RoomPlayer,Long>{
        boolean existsByRoomAndUser(Room room, User user);

    
}
