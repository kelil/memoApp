package com.khalil.memo.payload.requests;

import com.khalil.memo.models.Employee;

public class SignUpRequest {

    private String userName;

    private String role;


    private String password;

    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String string) {
        this.role = string;
    }
}

