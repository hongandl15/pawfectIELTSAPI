package com.pawfectielts.repositories;

import com.pawfectielts.entity.Recording;
import com.pawfectielts.entity.User;
import com.pawfectielts.service.RecordingService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordingRepository extends JpaRepository<Recording, Long> {
    Recording findByPartId(Long partId);
}
