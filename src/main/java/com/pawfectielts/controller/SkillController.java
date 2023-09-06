package com.pawfectielts.controller;

import com.pawfectielts.entity.Set;
import com.pawfectielts.entity.Skill;
import com.pawfectielts.repositories.SkillRepository;
import com.pawfectielts.service.impl.SetServiceImplement;
import com.pawfectielts.service.impl.SkillServiceImplement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/skill")
public class SkillController {

    private final SkillServiceImplement skillServiceImplement;

    private final SkillRepository skillRepository;

    public SkillController(SkillServiceImplement skillServiceImplement, SkillRepository skillRepository) {
        this.skillServiceImplement = skillServiceImplement;
        this.skillRepository = skillRepository;
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Skill>> getAllSkill() {
        List<Skill> response = skillServiceImplement.findAllSkill();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/getskill/{id}")
    public ResponseEntity<Skill> getSkillById(@PathVariable Long id) {
        Skill response = skillRepository.findById(id).orElse(null);
        return ResponseEntity.ok().body(response);
    }


}
