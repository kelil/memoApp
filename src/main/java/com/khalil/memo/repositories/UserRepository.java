package com.khalil.memo.repositories;

import com.khalil.memo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUserName(String username);

    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);
}
