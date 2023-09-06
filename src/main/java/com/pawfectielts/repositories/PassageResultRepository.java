package com.pawfectielts.repositories;

import com.pawfectielts.entity.Part;
import com.pawfectielts.entity.PassageResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassageResultRepository extends JpaRepository<PassageResult, Long> {
        List<PassageResult> findAllPassageResultByTestResultId(Long testResultId);
}
