package com.pawfectielts.service;

import com.pawfectielts.dto.AnswerDTO;
import com.pawfectielts.entity.Answer;
import com.pawfectielts.entity.TestResult;

import java.util.List;

public interface AnswerService {
    List<Answer> findAllAnswerByTestId(Long TestID);

    AnswerDTO createNewAnswerList(AnswerDTO answerDTO, Long testid);

    String createNewAnswer(String answer, int order, Long testid);
}
