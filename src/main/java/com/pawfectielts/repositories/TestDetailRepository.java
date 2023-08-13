package com.pawfectielts.repositories;

import com.pawfectielts.entity.TestDetail;
import com.pawfectielts.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestDetailRepository extends JpaRepository<TestDetail, Long> {
    TestDetail findTestDetailByTestId(Long testId);
}
