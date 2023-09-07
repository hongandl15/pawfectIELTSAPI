package com.pawfectielts.controller;

import com.pawfectielts.dto.AdminDTO.AddReadingTestRequest;
import com.pawfectielts.dto.AdminDTO.ListTestRequest;
import com.pawfectielts.entity.Answer;
import com.pawfectielts.entity.Set;
import com.pawfectielts.repositories.SetRepository;
import com.pawfectielts.service.impl.AdminServiceImplement;
import com.pawfectielts.service.impl.AnswerServiceImplement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminServiceImplement adminServiceImplement;
    private final SetRepository setRepository;
    public AdminController(AdminServiceImplement adminServiceImplement, SetRepository setRepository) {
        this.adminServiceImplement = adminServiceImplement;
        this.setRepository = setRepository;
    }

    @GetMapping("/set/getall")
    public ResponseEntity<List<Set>> getAllSet() {
        List<Set> response = setRepository.findAll();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/addtest")
    public ResponseEntity<String> getAnswer(@RequestBody ListTestRequest listTestRequest) {
        String response = adminServiceImplement.addTestFull(listTestRequest);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/addset")
    public ResponseEntity<String> addSet(@RequestBody Set set) {
        String response = adminServiceImplement.addNewSet(set);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/delete/{testid}")
    public ResponseEntity<String> addSet(@PathVariable Long testid) {
        String response = adminServiceImplement.deleteTest(testid);
        return ResponseEntity.ok().body(response);
    }

}
