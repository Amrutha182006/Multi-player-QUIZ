package com.amu.quizplatform.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.amu.quizplatform.entity.User;
import com.amu.quizplatform.repository.UserRepository;
import com.amu.quizplatform.security.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
          throws UsernameNotFoundException{

            User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

            return new CustomUserDetails(user);
          }
    
}
