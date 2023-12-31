package com.pawfectielts.repositories;

import com.pawfectielts.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long userId);
    boolean existsByEmail(String email);
    boolean existsByUsername(String Username);
    User findByUsername(String username);
}
