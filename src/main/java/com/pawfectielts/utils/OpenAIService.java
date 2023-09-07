package com.pawfectielts.utils;

import com.pawfectielts.dto.AnswerDTO;
import com.pawfectielts.dto.OpenAIDTO.*;
import com.pawfectielts.dto.QuestionDetailChildDTO;
import com.pawfectielts.entity.PassageResult;
import com.pawfectielts.entity.Test;
import com.pawfectielts.entity.TestResult;
import com.pawfectielts.entity.User;
import com.pawfectielts.repositories.PassageResultRepository;
import com.pawfectielts.repositories.TestRepository;
import com.pawfectielts.repositories.TestResultRepository;
import com.pawfectielts.service.impl.UserServiceImplement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class OpenAIService {
    private final String BASE_URL = "https://api.openai.com/v1/chat/completions";

    @Value("${API_KEY}")
    @Autowired
    private String API_KEY; // Replace with your actual OpenAI API key
    private RestTemplate restTemplate;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PassageResultRepository passageResultRepository;
    @Autowired
    private TestResultRepository testResultRepository;

    @Autowired
    private UserServiceImplement userServiceImplement;

    @Autowired
    private TestRepository testRepository;

    public OpenAIService() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    public OpenAiResponseDTO scoreWritingSpeaking(PassageInputDTO passageInput) {
        // gửi request lên openAI API và nhận response
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + API_KEY);
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<PassageInputDTO> requestEntity = new HttpEntity<>(passageInput, headers);
            ResponseEntity<OpenAiResponseDTO> response = restTemplate.exchange(BASE_URL, HttpMethod.POST, requestEntity, OpenAiResponseDTO.class);
            return response.getBody();
        }
        catch (Exception e){
            System.out.println(e.toString());
            return null;
        }

    }
    public PassageInputDTO convertPassagetoInputDTO(AnswerDTO answerDTO, int order){
        // Lấy passage và convert thành json input của open AI
        PassageInputDTO passageInputDTO = new PassageInputDTO();
        MessageDTO messageDTO = new MessageDTO();
        ArrayList<MessageDTO> messageDTOArrayList = new ArrayList<>();
        messageDTO.setContent(answerDTO.getAnswer().get(order));
        messageDTO.setRole("user");
        messageDTOArrayList.add(messageDTO);
        passageInputDTO.setModel("gpt-3.5-turbo");
        passageInputDTO.setMessages(messageDTOArrayList);
        return passageInputDTO;
    }

    public String getScoreContent(AnswerDTO answerDTO, int order){
        //Chấm điểm và lấy ra nhận xét
        PassageInputDTO passageInput = convertPassagetoInputDTO(answerDTO, order);
        OpenAiResponseDTO openAiResponseDTO = scoreWritingSpeaking(passageInput);
        String content = openAiResponseDTO.getChoices().get(0).getMessage().getContent();
        return content;
    };

    public TestResult extractScore(AnswerDTO answerDTO, Long testId, Long userId) {
        // Chấm điểm và lưu vào database
        User user = userServiceImplement.getUserById(userId);
        TestResult testResult = new TestResult();
        testResultRepository.save(testResult).getId();
        Test currentTest = testRepository.findById(testId).orElse(null);
        testResult.setTest(currentTest);
        testResult.setUser(user);
        testResult.setCreate(new Date());
        for(int i=0;i<answerDTO.getAnswer().size(); i++){
            String content = getScoreContent(answerDTO, i);
            List<Double> score1 = getScoreNumber(content);
            PassageResult passageResult = new PassageResult();
            try {
                passageResult.setScoreTaskResponse(score1.get(0));
                passageResult.setScoreCoherence(score1.get(1));
                passageResult.setScoreLexical(score1.get(2));
                passageResult.setScoreGrammar(score1.get(3));
                passageResult.setOverallScore(score1.get(4));
            }catch(Exception e){

            }
            passageResult.setContent(content);
            passageResult.setTestResult(testResult);
            passageResultRepository.save(passageResult);
        }
        return testResult;
    }

    public List<PassageResult> findPassageResultByTestResultId(Long TestResultId){
        return  passageResultRepository.findAllPassageResultByTestResultId(TestResultId);
    }

    public List<Double> getScoreNumber(String content){
        // Lấy các số chấm điểm trong nhận xét
        List<Double> numbers = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\b\\d+\\.?\\d*\\b"); // Regular expression to match integers
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            String matchedNumber = matcher.group();
            try {
                double parsedNumber = Double.parseDouble(matchedNumber);
                if (parsedNumber<=9.0)
                    numbers.add(parsedNumber); // Convert to int if it's a whole number
            } catch (NumberFormatException e) {
                // Ignore non-numeric matches
            }
        }
        return numbers;
    }

    public OverallScore convertToDTO(Long TestResultId){
        List<PassageResult> passageResultList = findPassageResultByTestResultId(TestResultId);
        List<ScoreDTO> scoreDTOList = passageResultList.stream()
                .map(scoreDTO -> modelMapper.map(scoreDTO, ScoreDTO.class))
                .collect(Collectors.toList());
        OverallScore overallScore = new OverallScore();
        overallScore.setListScore(scoreDTOList);
        return overallScore;
    }

}
