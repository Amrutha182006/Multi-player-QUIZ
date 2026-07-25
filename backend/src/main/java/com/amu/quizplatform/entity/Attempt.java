package com.amu.quizplatform.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
public class Attempt 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Quiz quiz;
    private Integer score;
    private Integer timeSpent;
    private LocalDateTime attemptedAt;
    @OneToMany(mappedBy="attempt")
    private List<AttemptAnswers>answers;

}
