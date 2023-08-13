package com.pawfectielts.controller;

import com.pawfectielts.entity.Set;
import com.pawfectielts.entity.Skill;
import com.pawfectielts.service.impl.SetServiceImplement;
import com.pawfectielts.service.impl.SkillServiceImplement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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
