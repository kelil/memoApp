package com.khalil.memo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.khalil.memo.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

}
