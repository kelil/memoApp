package com.khalil.memo.payload.responses;

import java.util.List;

import org.springframework.http.ResponseCookie;

public class UserInfoResponse {
    private Integer id;
    private String userName;
    private String email;
    private List<String> roles;
    private ResponseCookie jwtCookie;

    public UserInfoResponse(Integer id, String userName, List<String> roles, ResponseCookie jwtCookie) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.roles = roles;
        this.jwtCookie = jwtCookie;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getRoles() {
        return roles;
    }

    public ResponseCookie getDuration() {
        return jwtCookie;
    }

    public void setDuration(ResponseCookie jwtCookie) {
        this.jwtCookie = jwtCookie;
    }
}
