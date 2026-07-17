package com.amu.quizplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amu.quizplatform.entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz,Long>
{
    
}
