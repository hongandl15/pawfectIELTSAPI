package com.pawfectielts.repositories;

import com.pawfectielts.entity.QuestionType;
import com.pawfectielts.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface QuestionTypeRepository extends JpaRepository<QuestionType, Long> {

}
