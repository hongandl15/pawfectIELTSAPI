package com.pawfectielts.repositories;

import com.pawfectielts.entity.QuestionImage;
import com.pawfectielts.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface QuestionImageRepository extends JpaRepository<QuestionImage, Long> {

}
