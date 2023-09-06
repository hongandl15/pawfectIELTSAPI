package com.pawfectielts.repositories;

import com.pawfectielts.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAllByPartId(Long partId);
    Optional<Question> findById(Long id);

    void deleteByPart_Id(Long partId);
}
