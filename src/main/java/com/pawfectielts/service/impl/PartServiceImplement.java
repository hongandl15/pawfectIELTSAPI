package com.pawfectielts.service.impl;

import com.pawfectielts.entity.Part;
import com.pawfectielts.repositories.PartRepository;
import com.pawfectielts.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartServiceImplement implements PartService {

    @Autowired
    private PartRepository partRepository;
    @Override
    public List<Part> findPartByTestDetailId(Long testDetailId) {
        return partRepository.findByTestDetailId(testDetailId);
    }
}
