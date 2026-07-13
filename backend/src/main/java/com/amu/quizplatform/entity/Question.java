package com.amu.quizplatform.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String Question;
    @Column(nullable=false)
    private String OptionA;
    @Column(nullable=false)
    private String OptionB;
    @Column(nullable=false)
    private String OptionC;
    @Column(nullable=false)
    private String OptionD;
    @Column(nullable=false)
    private String CorrectAnswer;    
}
