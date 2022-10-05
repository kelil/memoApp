package com.khalil.memo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khalil.memo.models.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

}
