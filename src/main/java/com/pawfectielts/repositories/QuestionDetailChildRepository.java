package com.pawfectielts.repositories;

import com.pawfectielts.entity.QuestionDetail;
import com.pawfectielts.entity.QuestionDetailChild;
import com.pawfectielts.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDetailChildRepository extends JpaRepository<QuestionDetailChild, Long> {
//    List<QuestionDetailChild> findAllByQuestionDetailQuestionId(Long questionId);
    List<QuestionDetailChild> findByQuestionDetailId(Long questionDetailId);
}
