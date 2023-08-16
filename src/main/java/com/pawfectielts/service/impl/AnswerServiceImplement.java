package com.pawfectielts.service.impl;

import com.pawfectielts.dto.AnswerDTO;
import com.pawfectielts.dto.QuestionDTO;
import com.pawfectielts.dto.UserAnswerDTO;
import com.pawfectielts.entity.*;
import com.pawfectielts.entity.TestResultDTO;
import com.pawfectielts.repositories.AnswerRepository;
import com.pawfectielts.repositories.TestRepository;
import com.pawfectielts.repositories.TestResultRepository;
import com.pawfectielts.repositories.UserAnswerRepository;
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
    @Override
    public List<Answer> findAllAnswerByTestId(Long TestID) {
        return answerRepository.findAllByTestId(TestID);
    }


}
