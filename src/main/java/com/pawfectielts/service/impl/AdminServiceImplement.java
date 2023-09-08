package com.pawfectielts.service.impl;

import com.pawfectielts.dto.AdminDTO.*;
import com.pawfectielts.dto.QuestionDTO;
import com.pawfectielts.dto.QuestionDetailChildDTO;
import com.pawfectielts.dto.QuestionDetailDTO;
import com.pawfectielts.entity.*;
import com.pawfectielts.repositories.*;
import com.pawfectielts.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImplement implements AdminService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private SetRepository setRepository;

    @Autowired
    private QuestionRepository questionRepository;


    @Autowired
    private QuestionDetailRepository  questionDetailRepository;


    @Autowired
    private QuestionDetailChildRepository questionDetailChildRepository;

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private AnswerServiceImplement answerServiceImplement;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private TestResultRepository testResultRepository;

    @Autowired
    private UserAnswerRepository userAnswerRepository;

    @Autowired
    private PassageResultRepository passageResultRepository;

    private static final String UPLOAD_DIR = "src/main/resources/static/audio/";

    public String addNewSet(Set set){
        setRepository.save(set);
        return null;
    };

    @Override
    public String addTestFull(ListTestRequest listTestRequest) {
        Long testid = addTest(listTestRequest).getId();
        int order = 1;
        for (AddReadingTestRequest addReadingTestRequest: listTestRequest.getListTest()) {
            Long partid = addPart(addReadingTestRequest.getPart(), testid).getId();
            for (QuestionDTO questionDTO: addReadingTestRequest.getListQuestion()) {
                Question newQuestion = addQuestion(questionDTO, partid);
                for (QuestionDetailDTO questionDetailDTO : questionDTO.getQuestionDetails()) {
                    QuestionDetail newQuestionDetail = addQuestionDetail(questionDetailDTO, newQuestion.getId(), order);
                    answerServiceImplement.createNewAnswer(questionDetailDTO.getCorrectAnswer(), order, testid);
                    order+=1;
//                    for (QuestionDetailChildDTO questionDetailChildDTO: questionDetailDTO.getChild()) {
//                        QuestionDetailChild questionDetailChild = addQuestionDetailChild(questionDetailChildDTO, newQuestionDetail.getId());
//                    }
                }
            }
        }

        return testid.toString();
    }
    public String deleteSet(Long setId) {
        Set set = setRepository.findById(setId).orElse(null);
        List<Test> testList = testRepository.findAllTestBySetId(setId);
        for (Test test: testList) {
            deleteTest(test.getId());
        }
        setRepository.delete(set);
        return "deleted-set";
    }


    @Override
    public String deleteTest(Long testId) {
        Optional<Test> optionalTest = testRepository.findById(testId);
        if (optionalTest.isPresent()) {
            Test test = optionalTest.get();

            List<Answer> answers = answerRepository.findAllByTestId(testId);
            for (Answer answer: answers) {
                answerRepository.delete(answer);
            }
            List<TestResult> testResultList = testResultRepository.findByTestId(testId);
            for (TestResult testResult: testResultList) {
                List<UserAnswer> userAnswerList = userAnswerRepository.findAllByTestResultId(testResult.getId());
                for (UserAnswer useranswer: userAnswerList) {
                    userAnswerRepository.delete(useranswer);
                }
                List<PassageResult> passageResultList = passageResultRepository.findAllPassageResultByTestResultId(testResult.getId());
                for (PassageResult passageResult: passageResultList) {
                    passageResultRepository.delete(passageResult);
                }
                testResultRepository.delete(testResult);
            }


            List<Part> parts = partRepository.findAllByTestId(testId);
            for (Part part : parts) {
                List<Question> questions = questionRepository.findAllByPartId(part.getId());
                for (Question ques: questions) {
                    List<QuestionDetail> questionDetails = questionDetailRepository.findByQuestionId(ques.getId());
                    for (QuestionDetail quesDetail: questionDetails) {
                        List<QuestionDetailChild> questionDetailChildrens = questionDetailChildRepository.findByQuestionDetailId(quesDetail.getId());
                        for (QuestionDetailChild questionDetailChild: questionDetailChildrens) {
                            questionDetailChildRepository.delete(questionDetailChild);
                        }
                        questionDetailRepository.delete(quesDetail);
                    }
                    questionRepository.delete(ques);
                }
                partRepository.delete(part); // Delete associated Part
            }


            testRepository.delete(test); // Delete the Test entity
        }
        return "deleted-test";
    }

    public Test addTest(ListTestRequest listTestRequest){
        Test test = new Test();
        test.setSkill(skillRepository.findById(listTestRequest.getSkillid()).orElse(null));
        test.setSet(setRepository.findById(listTestRequest.getSetid()).orElse(null));
        test.setName(listTestRequest.getTestName());
        test.setCreate_at(new Date());
        return testRepository.save(test);
    }


    public Part addPart(PartDTO partDTO, Long testId) {
        Part newPart = new Part();
        newPart.setTest(testRepository.findById(testId).orElse(null));
        newPart.setContent(partDTO.getContent());
        newPart.setOrder(partDTO.getOrder());
//        newPart.setTopic(partDTO.getTopic());
        return partRepository.save(newPart);
    }

    public Question addQuestion(QuestionDTO questionDTO, long partId) {
        Question newQuestion = new Question();
        newQuestion.setPart(partRepository.findById(partId).orElse(null));
        newQuestion.setTitle(questionDTO.getTitle());
        newQuestion.setOrder(questionDTO.getOrder());
        return questionRepository.save(newQuestion);

    }

    public QuestionDetail addQuestionDetail(QuestionDetailDTO questionDetailDTO, Long questionid, int order){
        QuestionDetail newQuestionDetail = new QuestionDetail();
        newQuestionDetail.setQuestion(questionRepository.findById(questionid).orElse(null));
        newQuestionDetail.setName(questionDetailDTO.getName());
        newQuestionDetail.setOrder(order);
        return questionDetailRepository.save(newQuestionDetail);
    }

    public QuestionDetailChild addQuestionDetailChild(QuestionDetailChildDTO questionDetailChildDTO, Long questionDetailId){
        QuestionDetailChild newQuestionDetailChild = new QuestionDetailChild();
        newQuestionDetailChild.setQuestionDetail(questionDetailRepository.findById(questionDetailId).orElse(null));
        newQuestionDetailChild.setContent(questionDetailChildDTO.getContentChild());
        return questionDetailChildRepository.save(newQuestionDetailChild);
    }

}
