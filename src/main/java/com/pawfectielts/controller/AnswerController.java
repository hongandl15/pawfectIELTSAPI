package com.pawfectielts.controller;

import com.pawfectielts.entity.Answer;
import com.pawfectielts.entity.Part;
import com.pawfectielts.service.AnswerService;
import com.pawfectielts.service.impl.AnswerServiceImplement;
import com.pawfectielts.service.impl.PartServiceImplement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

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
}
