package com.pawfectielts.service.impl;

import com.pawfectielts.entity.Test;
import com.pawfectielts.entity.TestDetail;
import com.pawfectielts.repositories.PartRepository;
import com.pawfectielts.repositories.TestDetailRepository;
import com.pawfectielts.repositories.TestRepository;
import com.pawfectielts.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestServiceImplement implements TestService {
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private TestDetailRepository testDetailRepository;

    @Override
    public Test getTestByID(Long id){
        Optional<Test> test = testRepository.findById(id);
        return test.orElse(null);
    }

    @Override
    public TestDetail getTestDetailByTestID(Long testDetailId){
        TestDetail testDetail = testDetailRepository.findTestDetailByTestId(testDetailId);
        return testDetail;
    }

    @Override
    public List<Test> getAllTestBySetId(Long setId) {
        return testRepository.findAllTestBySetId(setId);
    }
}
