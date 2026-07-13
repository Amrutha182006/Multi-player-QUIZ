package com.amu.quizplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amu.quizplatform.entity.Question;

public interface QuestionRepository extends JpaRepository<Question,Long>
{
    
}
