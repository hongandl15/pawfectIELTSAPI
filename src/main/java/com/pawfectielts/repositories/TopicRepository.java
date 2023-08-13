package com.pawfectielts.repositories;

import com.pawfectielts.entity.Topic;
import com.pawfectielts.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

}
