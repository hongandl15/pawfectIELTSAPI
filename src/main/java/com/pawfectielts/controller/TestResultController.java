package com.pawfectielts.controller;

import com.pawfectielts.dto.AnswerDTO;
import com.pawfectielts.dto.OpenAIDTO.OverallScore;
import com.pawfectielts.entity.TestResult;
import com.pawfectielts.dto.TestResultDTO;
import com.pawfectielts.service.impl.TestResultImplement;
import com.pawfectielts.utils.OpenAIService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/result")
public class TestResultController {
    private final TestResultImplement testResultImplement;
    private final OpenAIService openAIService;


    public TestResultController(TestResultImplement testResultImplement, OpenAIService openAIService) {
        this.testResultImplement = testResultImplement;
        this.openAIService = openAIService;
    }

    @GetMapping("/get/{testResultId}")
    public ResponseEntity<TestResultDTO> getTestResultDTO(@PathVariable Long testResultId) {
        TestResultDTO response = testResultImplement.getTestResultDTO(testResultId);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/checkResult/{testId}")
    public ResponseEntity<TestResult> checkResult(@PathVariable Long testId, @RequestBody AnswerDTO answerDTO, @RequestParam Long userId) {
        TestResult response = testResultImplement.checkResult(testId, answerDTO, userId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/getall/{userId}")
    public ResponseEntity<List<TestResultDTO>> getAllTestResult(@PathVariable Long userId) {
        List<TestResultDTO> response = testResultImplement.findAllByUserId(userId);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/getpassagescore/{testid}")
    public ResponseEntity<TestResult> getPassageScore(@RequestBody AnswerDTO answerDTO, @PathVariable Long testid, @RequestParam Long userId) {
        AnswerDTO newAnswer = new AnswerDTO();
        ArrayList arrayList = new ArrayList<>();
        for (int i = 0; i < answerDTO.getTopic().size(); i++) {
            String messageContent = "Score my this ielts passage with 4 criteria Task Response, Coherence & Cohesion, Lexical Resource, Grammatical Range and Accuracy by number on 9.0 ielts score scale and show ielts overall for this topic: " + answerDTO.getTopic().get(i) + " my answer is: " + answerDTO.getAnswer().get(i);
            arrayList.add(messageContent);
        }
        newAnswer.setAnswer(arrayList);
        TestResult response = openAIService.extractScore(newAnswer, testid, userId);
        return ResponseEntity.ok().body(response);
    }


    @GetMapping("/getPassageResult/{testResultId}")
    public ResponseEntity<OverallScore> getPassageScore(@PathVariable Long testResultId) {
        OverallScore response = openAIService.convertToDTO(testResultId);
        return ResponseEntity.ok().body(response);
    }
}
