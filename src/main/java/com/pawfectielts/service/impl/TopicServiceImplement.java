package com.pawfectielts.service.impl;

import com.pawfectielts.entity.Set;
import com.pawfectielts.entity.Topic;
import com.pawfectielts.repositories.SetRepository;
import com.pawfectielts.repositories.TopicRepository;
import com.pawfectielts.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TopicServiceImplement implements TopicService {
    @Autowired
    private TopicRepository topicRepository;

    @Override
    public List<Topic> findAllTopic() {
        return topicRepository.findAll();
    }

}
