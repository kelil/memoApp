package com.khalil.memo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class UserRequest {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String userFirstName;
    private String userMiddleName;
    private String userLastName;
   

    @ManyToOne
    private UserRole currentRole;

    @ManyToOne
    private UserRole assignRole;


    public UserRequest() {
    }

    public UserRequest(String userFirstName, String userMiddleName, String userLastName, UserRole currentRole, UserRole assignRole) {
        this.userFirstName = userFirstName;
        this.userMiddleName = userMiddleName;
        this.userLastName = userLastName;
        this.currentRole = currentRole;
        this.assignRole = assignRole;
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserFirstName() {
        return this.userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserMiddleName() {
        return this.userMiddleName;
    }

    public void setUserMiddleName(String userMiddleName) {
        this.userMiddleName = userMiddleName;
    }

    public String getUserLastName() {
        return this.userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public UserRole getCurrentRole() {
        return this.currentRole;
    }

    public void setCurrentRole(UserRole currentRole) {
        this.currentRole = currentRole;
    }

    public UserRole getAssignRole() {
        return this.assignRole;
    }

    public void setAssignRole(UserRole assignRole) {
        this.assignRole = assignRole;
    }




}
