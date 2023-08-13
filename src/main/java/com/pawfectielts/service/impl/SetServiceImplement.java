package com.pawfectielts.service.impl;

import com.pawfectielts.entity.Set;
import com.pawfectielts.repositories.PartRepository;
import com.pawfectielts.repositories.SetRepository;
import com.pawfectielts.service.SetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetServiceImplement implements SetService {
    @Autowired
    private SetRepository setRepository;

    @Override
    public List<Set> findAllSet() {
        return setRepository.findAll();
    }
}
