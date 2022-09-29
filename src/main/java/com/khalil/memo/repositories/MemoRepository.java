package com.khalil.memo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khalil.memo.models.Memo;

public interface MemoRepository extends JpaRepository<Memo, Integer>{

    List<Memo> findAllByOrderByIdDesc();
    
}
