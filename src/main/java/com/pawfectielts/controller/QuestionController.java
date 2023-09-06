package com.pawfectielts.controller;


import com.pawfectielts.dto.QuestionDTO;
import com.pawfectielts.entity.Part;
import com.pawfectielts.entity.Question;
import com.pawfectielts.repositories.QuestionRepository;
import com.pawfectielts.service.PartService;
import com.pawfectielts.service.QuestionService;
import com.pawfectielts.service.impl.QuestionServiceImplement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/question")
public class QuestionController {

    private final QuestionServiceImplement questionServiceImplement;

    public QuestionController(QuestionServiceImplement questionServiceImplement) {
        this.questionServiceImplement = questionServiceImplement;
    }
    @GetMapping("/get/{partid}")
    public ResponseEntity<List<QuestionDTO>> getAllQuestionByPartId(@PathVariable Long partid) {
        List<QuestionDTO> response = questionServiceImplement.getListQuestion(partid);
        return ResponseEntity.ok().body(response);
    }
}
