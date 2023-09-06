package com.pawfectielts.repositories;

import com.pawfectielts.entity.Set;
import com.pawfectielts.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SetRepository extends JpaRepository<Set, Long> {

}
