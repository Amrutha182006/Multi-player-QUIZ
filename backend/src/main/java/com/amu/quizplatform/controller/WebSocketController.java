package com.amu.quizplatform.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    @MessageMapping("/hello")
    @SendTo("/topic/greeting")
    public String hello(String message) {
        System.out.println("MESSAGE RECEIVED: " + message);
        return "Server received: " + message;
    }
}
