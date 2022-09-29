package com.khalil.memo.controllers;

import com.khalil.memo.models.Division;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.khalil.memo.services.DivisionService;

@CrossOrigin(origins = "http://10.1.11.145:4200", maxAge = 300, allowCredentials = "true")
@RestController
@RequestMapping(path = "api/v1/divisions")
public class DivisionController {
    @Autowired
    private DivisionService divisionService;

    public DivisionController(DivisionService divisionService) {
        this.divisionService = divisionService;
    }

    @GetMapping()
    public ResponseEntity<?> getDivisions(){
        return ResponseEntity.ok(divisionService.findAllDivisions());
    }

    @GetMapping(value="/division/{id}")
    public ResponseEntity<?> getDivisionById(@PathVariable(value = "id") Integer id){
       return ResponseEntity.ok(divisionService.findDivision(id));
    }

    @PostMapping(value="/division")
    public ResponseEntity<?> getDivisionById(@RequestBody Division division){
        return ResponseEntity.ok(divisionService.addDivision(division));
    }
    
}
