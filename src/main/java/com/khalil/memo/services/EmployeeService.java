package com.khalil.memo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.khalil.memo.models.Employee;
import com.khalil.memo.repositories.EmployeeRepository;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAllEmployee() {
        return employeeRepository.findAll();
    }

    public Employee findEmployee(Integer id) {
        try {
            return employeeRepository.findById(id).get();
        } catch (Exception e) {
           return new Employee();
        }
       
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}
