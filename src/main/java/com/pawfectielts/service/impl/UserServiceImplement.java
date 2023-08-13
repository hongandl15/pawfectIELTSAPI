package com.pawfectielts.service.impl;

import com.pawfectielts.entity.Topic;
import com.pawfectielts.entity.User;
import com.pawfectielts.repositories.TopicRepository;
import com.pawfectielts.repositories.UserRepository;
import com.pawfectielts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplement implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElse(null);
    }
}
