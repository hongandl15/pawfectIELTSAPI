package com.pawfectielts.service;

import com.pawfectielts.entity.Test;

import java.util.List;

public interface TestService {
    Test getTestByID(Long id);
    List<Test> getAllTestBySetId(Long setId);
}
