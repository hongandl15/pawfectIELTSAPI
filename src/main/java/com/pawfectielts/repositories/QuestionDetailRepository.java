package com.pawfectielts.repositories;

import com.pawfectielts.entity.QuestionDetail;
import com.pawfectielts.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface QuestionDetailRepository extends JpaRepository<QuestionDetail, Long> {
    List<QuestionDetail> findByQuestionId(Long questionId);

    void deleteByQuestion_Id(Long questionId);


}
