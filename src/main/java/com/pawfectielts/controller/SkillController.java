package com.pawfectielts.controller;

import com.pawfectielts.entity.Set;
import com.pawfectielts.entity.Skill;
import com.pawfectielts.service.impl.SetServiceImplement;
import com.pawfectielts.service.impl.SkillServiceImplement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/skill")
public class SkillController {

    private final SkillServiceImplement skillServiceImplement;

    public SkillController(SkillServiceImplement skillServiceImplement) {
        this.skillServiceImplement = skillServiceImplement;
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Skill>> getAllSkill() {
        List<Skill> response = skillServiceImplement.findAllSkill();
        return ResponseEntity.ok().body(response);
    }


}
