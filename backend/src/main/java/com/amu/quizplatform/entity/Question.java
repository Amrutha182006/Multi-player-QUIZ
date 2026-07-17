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
    private String question;
    @Column(nullable=false)
    private String optiona;
    @Column(nullable=false)
    private String optionb;
    @Column(nullable=false)
    private String optionc;
    @Column(nullable=false)
    private String optiond;
    @Column(nullable=false)
    private String correct_answer;    
}
