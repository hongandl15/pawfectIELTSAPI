package com.pawfectielts.controller;


import com.pawfectielts.entity.Topic;
import com.pawfectielts.entity.User;
import com.pawfectielts.service.UserService;
import com.pawfectielts.service.impl.TopicServiceImplement;
import com.pawfectielts.service.impl.UserServiceImplement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {

    private final UserServiceImplement userServiceImplement;

    public UserController(UserServiceImplement userServiceImplement) {
        this.userServiceImplement = userServiceImplement;
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUser(Long id) {
        User response = userServiceImplement.getUserById(id);
        return ResponseEntity.ok().body(response);
    }
}
