package com.pawfectielts.controller;

import com.pawfectielts.dto.QuestionDTO;
import com.pawfectielts.entity.Set;
import com.pawfectielts.service.SetService;
import com.pawfectielts.service.impl.QuestionServiceImplement;
import com.pawfectielts.service.impl.SetServiceImplement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/set")
public class SetController {
    private final SetServiceImplement setServiceImplement;

    public SetController(SetServiceImplement setServiceImplement) {
        this.setServiceImplement = setServiceImplement;
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Set>> getAllSet() {
        List<Set> response = setServiceImplement.findAllSet();
        return ResponseEntity.ok().body(response);
    }
}
