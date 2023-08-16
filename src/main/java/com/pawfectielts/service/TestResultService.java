package com.pawfectielts.service;

import com.pawfectielts.dto.AnswerDTO;
import com.pawfectielts.dto.UserAnswerDTO;
import com.pawfectielts.entity.TestResult;
import com.pawfectielts.entity.TestResultDTO;
import com.pawfectielts.entity.UserAnswer;

import java.util.ArrayList;

public interface TestResultService {
    TestResult checkResult(Long testId, AnswerDTO answerDTO);
    UserAnswer addUserAnwser(Long testResultId, UserAnswer userAnswer);
    TestResultDTO getTestResultDTO(Long testResultId);
    ArrayList<UserAnswerDTO> getAllUserAnswerByTestResult(Long testResultId);
    UserAnswerDTO convertEntityToDTO(Long userAnswerId);
    TestResult getTestResultByID(Long testResultId);
    double ScoreTest(int numberOfCorrect);
}
