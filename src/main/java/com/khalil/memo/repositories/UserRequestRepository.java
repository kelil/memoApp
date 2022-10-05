package com.khalil.memo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khalil.memo.models.UserRequest;

public interface UserRequestRepository extends JpaRepository<UserRequest,Integer> {

}
