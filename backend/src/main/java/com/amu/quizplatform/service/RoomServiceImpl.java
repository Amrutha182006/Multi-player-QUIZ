package com.amu.quizplatform.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.amu.quizplatform.dto.CreateRoomRequestDTO;
import com.amu.quizplatform.dto.RoomDTO;
import com.amu.quizplatform.entity.Quiz;
import com.amu.quizplatform.entity.Room;
import com.amu.quizplatform.entity.RoomStatus;
import com.amu.quizplatform.entity.User;
import com.amu.quizplatform.repository.QuizRepository;
import com.amu.quizplatform.repository.RoomRepository;
import com.amu.quizplatform.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;

    @Override
    public RoomDTO createRoom(CreateRoomRequestDTO request) {
        Quiz quiz = quizRepository.findById(request.getQuizId())
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String username = authentication.getName();

        User host = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Room room = new Room();

        room.setRoomCode(generateRoomCode());
        room.setQuiz(quiz);
        room.setHost(host);
        room.setStatus(RoomStatus.WAITING);
        room.setCreatedAt(LocalDateTime.now());
        room.setMaxPlayers(request.getMaxPlayers());

        roomRepository.save(room);

        RoomDTO dto = new RoomDTO();

        dto.setId(room.getId());
        dto.setRoomCode(room.getRoomCode());
        dto.setQuizTitle(quiz.getTitle());
        dto.setHostUsername(host.getUsername());
        dto.setStatus(room.getStatus());
        dto.setMaxPlayers(room.getMaxPlayers());
        dto.setCurrentPlayers(0);

        return dto;
    }

    private String generateRoomCode() {

        return UUID.randomUUID()
                .toString()
                .substring(0, 6)
                .toUpperCase();
    }

}
