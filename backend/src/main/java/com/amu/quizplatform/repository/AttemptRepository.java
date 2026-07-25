package com.amu.quizplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amu.quizplatform.entity.Attempt;

public interface AttemptRepository extends JpaRepository<Attempt,Long>{
    
}
