package com.khalil.memo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.khalil.memo.models.Division;
import com.khalil.memo.repositories.DivisionRepository;

@Service
public class DivisionService {

    private DivisionRepository divisionRepository;

    public DivisionService(DivisionRepository divisionRepository) {
        this.divisionRepository = divisionRepository;
    }

    public Division findDivision(Integer from) {
        return divisionRepository.findById(from).get();
    }

    public List<Division> findAllDivisions() {
        return divisionRepository.findAll();
    }

    public Division addDivision(Division division) {
        return divisionRepository.save(division);
    }
}
