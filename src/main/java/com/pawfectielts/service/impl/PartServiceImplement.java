package com.pawfectielts.service.impl;

import com.pawfectielts.dto.AdminDTO.PartDTO;
import com.pawfectielts.dto.QuestionDetailChildDTO;
import com.pawfectielts.entity.Part;
import com.pawfectielts.entity.QuestionDetailChild;
import com.pawfectielts.repositories.PartRepository;
import com.pawfectielts.service.PartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartServiceImplement implements PartService {
    @Autowired
    private PartRepository partRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PartDTO> convertToDTO(Long testId) {
        List<Part> listPart =partRepository.findAllByTestIdOrderByOrderAsc(testId);
        return listPart.stream()
                .map(partEntity  -> modelMapper.map(partEntity , PartDTO.class))
                .collect(Collectors.toList());
    }

}
