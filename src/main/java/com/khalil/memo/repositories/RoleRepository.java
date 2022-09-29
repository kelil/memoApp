package com.khalil.memo.repositories;

import com.khalil.memo.models.ERole;
import com.khalil.memo.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(ERole roleAdmin);
}
