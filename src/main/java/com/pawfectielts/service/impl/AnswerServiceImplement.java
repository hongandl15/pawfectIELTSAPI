package com.pawfectielts.service.impl;

import com.pawfectielts.dto.AnswerDTO;
import com.pawfectielts.entity.Answer;
import com.pawfectielts.entity.TestResult;
import com.pawfectielts.repositories.AnswerRepository;
import com.pawfectielts.repositories.PartRepository;
import com.pawfectielts.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AnswerServiceImplement implements AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public List<Answer> findAllAnswerByTestId(Long TestID) {
        return answerRepository.findByTestId(TestID);
    }

    @Override
    public TestResult checkResult(AnswerDTO answerDTO) {
        return null;
    }
}
