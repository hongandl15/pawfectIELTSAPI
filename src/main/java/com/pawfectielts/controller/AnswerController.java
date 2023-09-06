package com.pawfectielts.controller;

import com.pawfectielts.dto.AnswerDTO;
import com.pawfectielts.entity.Answer;
import com.pawfectielts.entity.Part;
import com.pawfectielts.entity.TestResult;
import com.pawfectielts.service.AnswerService;
import com.pawfectielts.service.impl.AnswerServiceImplement;
import com.pawfectielts.service.impl.PartServiceImplement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/answer")
public class AnswerController {
    private final AnswerServiceImplement answerServiceImplement;

    public AnswerController(AnswerServiceImplement answerServiceImplement) {
        this.answerServiceImplement = answerServiceImplement;
    }
    @GetMapping("/get/{testid}")
    public ResponseEntity<List<Answer>> getAnswer(@PathVariable Long testid) {
        List<Answer> response = answerServiceImplement.findAllAnswerByTestId(testid);
        return ResponseEntity.ok().body(response);
    }
//    @CrossOrigin(origins = "http://localhost:3000")

}
