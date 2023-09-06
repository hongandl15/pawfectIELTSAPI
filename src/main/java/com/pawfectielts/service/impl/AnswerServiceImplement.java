package com.pawfectielts.service.impl;

import com.pawfectielts.dto.AnswerDTO;
import com.pawfectielts.entity.*;
import com.pawfectielts.repositories.AnswerRepository;
import com.pawfectielts.repositories.TestRepository;
import com.pawfectielts.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImplement implements AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private TestRepository testRepository ;
    @Override
    public List<Answer> findAllAnswerByTestId(Long TestID) {
        return answerRepository.findAllByTestId(TestID);
    }

    @Override
    public AnswerDTO createNewAnswerList(AnswerDTO answerDTO, Long testid) {
        int order = 1;
        for (String answer : answerDTO.getAnswer()) {
            Answer newanswer = new Answer();
            newanswer.setCorrectAnswer(answer);
            newanswer.setOrder(order);
            newanswer.setTest(testRepository.findById(testid).orElse(null));
        }
        return answerDTO;
    }

    @Override
    public String createNewAnswer(String answer, int order, Long testid) {
            Answer newanswer = new Answer();
            newanswer.setCorrectAnswer(answer);
            newanswer.setOrder(order);
            newanswer.setTest(testRepository.findById(testid).orElse(null));
            answerRepository.save(newanswer);
        return answer;
    }
}
