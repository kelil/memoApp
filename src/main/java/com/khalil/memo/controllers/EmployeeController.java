package com.khalil.memo.controllers;

import java.util.List;

import com.khalil.memo.models.Division;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.khalil.memo.models.Employee;
import com.khalil.memo.services.EmployeeService;

@CrossOrigin(origins = "http://10.1.11.145:4200", maxAge = 300, allowCredentials = "true")
@RestController
@RequestMapping(path = "api/v1/employees")
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public List<Employee> getAllEmployee(){
        return employeeService.findAllEmployee();
    }

    @GetMapping(value = "/employee/{id}")
    public Employee getEmployeeById(@PathVariable(value = "id") Integer id){
        return employeeService.findEmployee(id);
    }

    @PostMapping(value="/employee")
    public ResponseEntity<?> getDivisionById(@RequestBody Employee employee){
        return ResponseEntity.ok(employeeService.addEmployee(employee));
    }
}
