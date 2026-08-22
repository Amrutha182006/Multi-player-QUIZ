package com.amu.quizplatform.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.amu.quizplatform.dto.CreateRoomRequestDTO;
import com.amu.quizplatform.dto.RoomDTO;
import com.amu.quizplatform.dto.RoomEventDTO;
import com.amu.quizplatform.entity.Quiz;
import com.amu.quizplatform.entity.Room;
import com.amu.quizplatform.entity.RoomPlayer;
import com.amu.quizplatform.entity.RoomStatus;
import com.amu.quizplatform.entity.User;
import com.amu.quizplatform.repository.QuizRepository;
import com.amu.quizplatform.repository.RoomPlayerRepository;
import com.amu.quizplatform.repository.RoomRepository;
import com.amu.quizplatform.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomPlayerRepository roomPlayerRepository;
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    private final SimpMessagingTemplate messagingTemplate;

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
        dto.setHostUsername(username);
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

    @Override
    public Room showRoom(String roomCode) {
        Room room = roomRepository.findByRoomCode(roomCode).orElseThrow(() -> new RuntimeException("Room not found"));
        return room;
    }

    @Override
    public RoomDTO joinRoom(String roomCode) {
        Room room = roomRepository.findByRoomCode(roomCode)
                .orElseThrow(() -> new RuntimeException("Room not found"));
        if (room.getStatus() != RoomStatus.WAITING) {
            throw new RuntimeException("Room has already started");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (roomPlayerRepository.existsByRoomAndUser(room, user)) {
            throw new RuntimeException("User already joined this room");
        }
        if (room.getPlayers().size() >= room.getMaxPlayers()) {
            throw new RuntimeException("Room is full");
        }
        RoomPlayer roomPlayer = new RoomPlayer();

        roomPlayer.setRoom(room);
        roomPlayer.setUser(user);

        room.getPlayers().add(roomPlayer);
        roomPlayerRepository.save(roomPlayer);

        RoomEventDTO event = new RoomEventDTO();

        event.setType("PLAYER_JOINED");
        event.setRoomCode(room.getRoomCode());
        event.setUsername(username);
        event.setCurrentPlayers(room.getPlayers().size());

        messagingTemplate.convertAndSend(
                "/topic/room/" + room.getRoomCode(),
                event);

        RoomDTO dto = new RoomDTO();

        dto.setId(room.getId());
        dto.setRoomCode(room.getRoomCode());
        dto.setQuizTitle(room.getQuiz().getTitle());
        dto.setHostUsername(username);
        dto.setStatus(room.getStatus());
        dto.setMaxPlayers(room.getMaxPlayers());
        dto.setCurrentPlayers(room.getPlayers().size());

        return dto;
    }

    @Override
    public RoomDTO startRoom(String roomCode) {
        Room room = roomRepository.findByRoomCode(roomCode).orElseThrow(() -> new RuntimeException("Room not found"));
        if (room.getStatus() != RoomStatus.WAITING) {
            throw new RuntimeException("Room has already started or finished");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        if (!room.getHost().getUsername().equals(username)) {
            throw new RuntimeException("Only the host can start the quiz");
        }
        room.setStatus(RoomStatus.IN_PROGRESS);
        roomRepository.save(room);

        RoomEventDTO event = new RoomEventDTO();

        event.setType("QUIZ_STARTED");
        event.setRoomCode(room.getRoomCode());
        event.setUsername(username);
        event.setCurrentPlayers(room.getPlayers().size());

        messagingTemplate.convertAndSend("/topic/room/" + room.getRoomCode(), event);

        RoomDTO dto = new RoomDTO();

        dto.setId(room.getId());
        dto.setRoomCode(room.getRoomCode());
        dto.setQuizTitle(room.getQuiz().getTitle());
        dto.setHostUsername(username);
        dto.setStatus(room.getStatus());
        dto.setMaxPlayers(room.getMaxPlayers());
        dto.setCurrentPlayers(room.getMaxPlayers());
        dto.setCurrentPlayers(room.getPlayers().size());

        return dto;
    }

}
