package com.pawfectielts.service.impl;

import com.pawfectielts.entity.Set;
import com.pawfectielts.entity.Skill;
import com.pawfectielts.repositories.SetRepository;
import com.pawfectielts.repositories.SkillRepository;
import com.pawfectielts.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImplement implements SkillService {
    @Autowired
    private SkillRepository skillRepository;

    @Override
    public List<Skill> findAllSkill() {
        return skillRepository.findAll();
    }


}
