package com.pawfectielts.service;

import com.pawfectielts.entity.Part;

import java.util.List;

public interface PartService {
    List<Part> findPartByTestDetailId(Long testDetailId);
}
