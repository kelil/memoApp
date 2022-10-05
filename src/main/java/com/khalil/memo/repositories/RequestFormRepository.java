package com.khalil.memo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khalil.memo.models.RequestFormMemo;

public interface RequestFormRepository extends JpaRepository<RequestFormMemo, Integer> {

}
