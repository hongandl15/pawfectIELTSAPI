package com.pawfectielts.repositories;

import com.pawfectielts.entity.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Long> {
    List<TestResult> findByTestId(Long testId);

    List<TestResult> findByUserId(Long userId);

    List<TestResult> findAllByUser_IdOrderByCreateDesc(Long userId);


}
