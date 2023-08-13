package com.pawfectielts.repositories;

import com.pawfectielts.entity.Answer;
import com.pawfectielts.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByTestId(Long testID);
        
}
