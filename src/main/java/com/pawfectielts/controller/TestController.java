package com.pawfectielts.controller;

import com.pawfectielts.entity.Skill;
import com.pawfectielts.entity.Test;
import com.pawfectielts.service.impl.SkillServiceImplement;
import com.pawfectielts.service.impl.TestServiceImplement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/test")
public class TestController {

    private final TestServiceImplement testServiceImplement;

    public TestController(TestServiceImplement testServiceImplement) {
        this.testServiceImplement = testServiceImplement;
    }
    @GetMapping("/getbyset/{setid}")
    public ResponseEntity<List<Test>> getBySetId(@PathVariable Long setid) {
        List<Test> response = testServiceImplement.getAllTestBySetId(setid);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Test> getById(@PathVariable Long id) {
        Test response = testServiceImplement.getTestByID(id);
        return ResponseEntity.ok().body(response);
    }
}
