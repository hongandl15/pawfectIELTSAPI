package com.pawfectielts.repositories;

import com.pawfectielts.entity.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Long> {
    TestResult findByTestId(Long testID);
}
