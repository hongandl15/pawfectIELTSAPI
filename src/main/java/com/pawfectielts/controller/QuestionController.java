package com.pawfectielts.controller;


import com.pawfectielts.dto.QuestionDTO;
import com.pawfectielts.entity.Part;
import com.pawfectielts.entity.Question;
import com.pawfectielts.repositories.QuestionRepository;
import com.pawfectielts.service.PartService;
import com.pawfectielts.service.QuestionService;
import com.pawfectielts.service.impl.QuestionServiceImplement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QuestionServiceImplement questionServiceImplement;

    public QuestionController(QuestionServiceImplement questionServiceImplement) {
        this.questionServiceImplement = questionServiceImplement;
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionDTO>> getAllQuestion(@PathVariable Long id) {
        List<QuestionDTO> response = questionServiceImplement.getListQuestion(id);
        return ResponseEntity.ok().body(response);
    }
}
