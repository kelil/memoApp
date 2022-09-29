package com.khalil.memo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khalil.memo.models.Division;

public interface DivisionRepository extends JpaRepository<Division, Integer> {

}
