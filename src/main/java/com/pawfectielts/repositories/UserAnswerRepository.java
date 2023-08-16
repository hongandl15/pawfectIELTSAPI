package com.pawfectielts.repositories;

import com.pawfectielts.entity.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
    ArrayList<UserAnswer> findAllByTestResultId(Long testResultId);
}
