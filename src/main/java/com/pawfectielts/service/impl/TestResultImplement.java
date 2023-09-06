package com.pawfectielts.service.impl;

import com.pawfectielts.dto.AnswerDTO;
import com.pawfectielts.dto.QuestionDetailChildDTO;
import com.pawfectielts.dto.UserAnswerDTO;
import com.pawfectielts.entity.*;
import com.pawfectielts.dto.TestResultDTO;
import com.pawfectielts.repositories.AnswerRepository;
import com.pawfectielts.repositories.TestRepository;
import com.pawfectielts.repositories.TestResultRepository;
import com.pawfectielts.repositories.UserAnswerRepository;
import com.pawfectielts.service.TestResultService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired
    private UserServiceImplement userServiceImplement;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TestResult checkResult(Long testId, AnswerDTO answerDTO, Long userId) {
        User user = userServiceImplement.getUserById(userId);
        List<Answer> correctAnswer = answerServiceImplement.findAllAnswerByTestId(testId);
        TestResult testResult = new TestResult();
        Long testResultId = testResultRepository.save(testResult).getId();
        Test currentTest = testRepository.findById(testId).orElse(null);
        int numberOfCorrect = 0;
        int numberOfSkip = 0;
        for (int i = 0; i <= 39; i++) {
            UserAnswer userAnswer = new UserAnswer();
            userAnswer.setAnswer(answerDTO.getAnswer().get(i).toUpperCase().trim());
            if(correctAnswer.get(i).getCorrectAnswer() != null ){
                userAnswer.setCorrectAnswer(correctAnswer.get(i).getCorrectAnswer().toUpperCase());
            }
            else{
                userAnswer.setCorrectAnswer("");
            }

            userAnswer.setOrderNumber(i+1);
            if (userAnswer.getAnswer().equals(userAnswer.getCorrectAnswer())){
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
        testResult.setUser(user);
        testResult.setCreate(new Date());

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
        ArrayList<UserAnswerDTO> userAnswerDTOArrayList = getAllUserAnswerByTestResult(testResultId);
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
        response.setCorrectAnswer(userAnswer.get().getCorrectAnswer());
        return response;
    }

    @Override
    public TestResult getTestResultByID(Long testResultId) {
        return testResultRepository.findById(testResultId).orElse(null);
    }

    public List<TestResultDTO> findAllByUserId(Long userId){
        List<TestResult> testResultList =  testResultRepository.findAllByUser_IdOrderByCreateDesc(userId);
        List<TestResultDTO> testResultDTOS = new ArrayList<>();
        for (TestResult testResult: testResultList) {
            TestResultDTO testResultDTO = new TestResultDTO();
            ArrayList<UserAnswerDTO> userAnswerDTOArrayList = getAllUserAnswerByTestResult(testResult.getId());

            testResultDTO.setRightAnswer(testResult.getRightAnswer());
            testResultDTO.setWrongAnswer(testResult.getWrongAnswer());
            testResultDTO.setSkipAnswer(testResult.getSkipAnswer());
            testResultDTO.setScore(testResult.getScore());
            testResultDTO.setUserAnswers(userAnswerDTOArrayList);
            testResultDTO.setTestid(testResult.getTest().getId());
            testResultDTO.setUserid(testResult.getUser().getId());
            testResultDTO.setId(testResult.getId());
            testResultDTO.setTestName(testResult.getTest().getName());
            testResultDTO.setCreate_at(testResult.getCreate().toString());
            testResultDTOS.add(testResultDTO);
        }
        return testResultDTOS;
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
