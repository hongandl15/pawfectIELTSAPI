package com.pawfectielts.service.impl;

import com.pawfectielts.dto.AnswerDTO;
import com.pawfectielts.dto.UserAnswerDTO;
import com.pawfectielts.entity.*;
import com.pawfectielts.entity.TestResultDTO;
import com.pawfectielts.repositories.AnswerRepository;
import com.pawfectielts.repositories.TestRepository;
import com.pawfectielts.repositories.TestResultRepository;
import com.pawfectielts.repositories.UserAnswerRepository;
import com.pawfectielts.service.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TestResultImplement implements TestResultService {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private TestResultRepository testResultRepository;
    @Autowired
    private UserAnswerRepository userAnswerRepository;

    @Autowired
    private AnswerServiceImplement answerServiceImplement;

    @Override
    public TestResult checkResult(Long testId, AnswerDTO answerDTO) {
        List<Answer> correctAnswer = answerServiceImplement.findAllAnswerByTestId(testId);
        TestResult testResult = new TestResult();
        Long testResultId = testResultRepository.save(testResult).getId();
        Test currentTest = testRepository.findById(testId).orElse(null);
        int numberOfCorrect = 0;
        int numberOfSkip = 0;
        for (int i = 0; i <= 39; i++) {
            UserAnswer userAnswer = new UserAnswer();
            userAnswer.setAnswer(answerDTO.getAnswer().get(i).toUpperCase().trim());
            userAnswer.setOrderNumber(i+1);
            if (answerDTO.getAnswer().get(i).toUpperCase().trim().equals(correctAnswer.get(i).getCorrectAnswer().toUpperCase())){
                numberOfCorrect += 1;
                userAnswer.setCorrect(true);
            }
            else if (answerDTO.getAnswer().get(i).equals("")){
                numberOfSkip += 1;
                userAnswer.setCorrect(false);
            }
            else userAnswer.setCorrect(false);
            addUserAnwser(testResultId, userAnswer);
        }
        testResult.setTest(currentTest);
        testResult.setScore(ScoreTest(numberOfCorrect));
        testResult.setRightAnswer(numberOfCorrect);
        testResult.setWrongAnswer(40-numberOfCorrect-numberOfSkip);
        testResult.setSkipAnswer(numberOfSkip);
        testResultRepository.save(testResult);
        return testResult;
    }
    @Override
    public UserAnswer addUserAnwser(Long testResultId, UserAnswer userAnswer){
        TestResult testResult = testResultRepository.findById(testResultId).orElse(null);
        userAnswer.setTestResult(testResult);
        userAnswerRepository.save(userAnswer);
        return null;
    }
    @Override
    public TestResultDTO getTestResultDTO(Long testResultId){
        TestResult testResult = getTestResultByID(testResultId);
        ArrayList<UserAnswerDTO> userAnswerDTOArrayList = getAllUserAnswerByTestResult(testResult.getId());
        TestResultDTO testResultDTO = new TestResultDTO();
        testResultDTO.setRightAnswer(testResult.getRightAnswer());
        testResultDTO.setWrongAnswer(testResult.getWrongAnswer());
        testResultDTO.setSkipAnswer(testResult.getSkipAnswer());
        testResultDTO.setScore(testResult.getScore());
        testResultDTO.setUserAnswers(userAnswerDTOArrayList);
        testResultDTO.setTestid(testResult.getTest().getId());
        return testResultDTO;
    }

    @Override
    public ArrayList<UserAnswerDTO> getAllUserAnswerByTestResult(Long testResultId){
        ArrayList<UserAnswer> userAnswerArrayList = userAnswerRepository.findAllByTestResultId(testResultId);
        ArrayList<UserAnswerDTO> userAnswerDTOArrayList = new ArrayList<>();
        for (UserAnswer userAnswer: userAnswerArrayList) {
            UserAnswerDTO userAnswerDTO = convertEntityToDTO(userAnswer.getId());
            userAnswerDTOArrayList.add(userAnswerDTO);
        }
        return userAnswerDTOArrayList;
    }

    @Override
    public UserAnswerDTO convertEntityToDTO(Long userAnswerId) {
        UserAnswerDTO response = new UserAnswerDTO();
        Optional<UserAnswer> userAnswer = userAnswerRepository.findById(userAnswerId);
        response.setAnswer(userAnswer.get().getAnswer());
        response.setOrderNumber(userAnswer.get().getOrderNumber());
        response.setCorrect(userAnswer.get().isCorrect());
        return response;
    }

    @Override
    public TestResult getTestResultByID(Long testResultId) {
        return testResultRepository.findById(testResultId).orElse(null);
    }
    @Override
    public double ScoreTest(int numberOfCorrect){
        double score = 0;
        if(numberOfCorrect == 0)
            return 0.0;
        else if (numberOfCorrect <3)
            return 1.5;
        else if (numberOfCorrect <= 4)
            return 2.5;
        else if (numberOfCorrect <= 9)
            return 3.5;
        else if (numberOfCorrect <= 12)
            return 4.0;
        else if (numberOfCorrect <= 15)
            return 4.5;
        else if (numberOfCorrect <= 19)
            return 5.0;
        else if (numberOfCorrect <= 22)
            return 5.5;
        else if (numberOfCorrect <= 26)
            return 6.0;
        else if (numberOfCorrect <= 29)
            return 6.5;
        else if (numberOfCorrect <= 32)
            return 7.0;
        else if (numberOfCorrect <= 34)
            return 7.5;
        else if (numberOfCorrect <= 36)
            return 8.0;
        else if (numberOfCorrect <= 38)
            return 8.5;
        else return 9.0;
    }
}
