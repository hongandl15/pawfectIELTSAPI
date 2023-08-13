package com.pawfectielts.repositories;

import com.pawfectielts.entity.Part;
import com.pawfectielts.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {
//    @Query("SELECT p FROM Part p WHERE p.testDetail.id = :testDetailId")
    List<Part> findByTestDetailId(Long testDetailId);
}
