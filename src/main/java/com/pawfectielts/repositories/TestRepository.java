package com.pawfectielts.repositories;

import com.pawfectielts.entity.Test;
import com.pawfectielts.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findAllTestBySetId(Long setId);
}
