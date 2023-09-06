package com.pawfectielts.service;

import com.pawfectielts.dto.AdminDTO.PartDTO;
import com.pawfectielts.entity.Part;

import java.util.List;

public interface PartService {
    List<PartDTO> convertToDTO(Long testId);
}
