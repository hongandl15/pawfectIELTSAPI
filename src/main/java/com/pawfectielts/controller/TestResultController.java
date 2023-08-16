package com.pawfectielts.controller;

import com.pawfectielts.dto.AnswerDTO;
import com.pawfectielts.entity.Answer;
import com.pawfectielts.entity.Part;
import com.pawfectielts.entity.TestResult;
import com.pawfectielts.entity.TestResultDTO;
import com.pawfectielts.service.AnswerService;
import com.pawfectielts.service.impl.AnswerServiceImplement;
import com.pawfectielts.service.impl.PartServiceImplement;
import com.pawfectielts.service.impl.TestResultImplement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/result")
public class TestResultController {
    private final TestResultImplement testResultImplement;


    public TestResultController(TestResultImplement testResultImplement) {
        this.testResultImplement = testResultImplement;
    }

    @GetMapping("/get/{testResultId}")
    public ResponseEntity<TestResultDTO> checkResult(@PathVariable Long testResultId) {
        TestResultDTO response = testResultImplement.getTestResultDTO(testResultId);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/checkResult/{testId}")
    public ResponseEntity<TestResult> checkResult(@PathVariable Long testId, @RequestBody AnswerDTO answerDTO) {
        TestResult response = testResultImplement.checkResult(testId, answerDTO);
        return ResponseEntity.ok().body(response);
    }

}
