package com.pawfectielts.controller;

import com.pawfectielts.entity.Set;
import com.pawfectielts.entity.Topic;
import com.pawfectielts.service.TopicService;
import com.pawfectielts.service.impl.SetServiceImplement;
import com.pawfectielts.service.impl.TopicServiceImplement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/topic")
public class TopicController {
    private final TopicServiceImplement topicServiceImplement;

    public TopicController(TopicServiceImplement topicServiceImplement) {
        this.topicServiceImplement = topicServiceImplement;
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Topic>> getAllTopic() {
        List<Topic> response = topicServiceImplement.findAllTopic();
        return ResponseEntity.ok().body(response);
    }
}
