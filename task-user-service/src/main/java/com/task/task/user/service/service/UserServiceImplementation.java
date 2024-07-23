package com.task.task.user.service.service;

import com.task.task.user.service.config.JwtProvider;
import com.task.task.user.service.modal.User;
import com.task.task.user.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserProfile(String jwt) {
        String email=JwtProvider.getEmailFromJWTToken(jwt);
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
