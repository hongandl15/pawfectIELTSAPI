package com.pawfectielts.service.impl;

import com.pawfectielts.entity.User;
import com.pawfectielts.repositories.UserRepository;
import com.pawfectielts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplement implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User validateLogin(String username, String password) {
        return null;
    }

    public User register(User user){
        return userRepository.save(user);
    };
}
