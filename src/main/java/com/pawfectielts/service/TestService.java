package com.pawfectielts.service;

import com.pawfectielts.entity.Test;
import com.pawfectielts.entity.TestDetail;

import java.util.List;

public interface TestService {
    Test getTestByID(Long id);
    TestDetail getTestDetailByTestID(Long testDetailId);

    List<Test> getAllTestBySetId(Long setId);
}
