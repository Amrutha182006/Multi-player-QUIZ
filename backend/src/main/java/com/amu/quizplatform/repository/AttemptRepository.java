package com.amu.quizplatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amu.quizplatform.entity.Attempt;
import com.amu.quizplatform.entity.User;

public interface AttemptRepository extends JpaRepository<Attempt,Long>{

    List<Attempt> findByUser(User user);    
}
