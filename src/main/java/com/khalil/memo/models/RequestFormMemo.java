package com.khalil.memo.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table
public class RequestFormMemo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String subject;
    @ManyToOne()
    private Division from;
    @ManyToOne()
    private Division to;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "request_form_memo_division", joinColumns = {
            @JoinColumn(name = "request_form_memo_id") }, inverseJoinColumns = {
                    @JoinColumn(name = "division_id") })
    private Set<Division> cc = new HashSet<>();

    @OneToMany
    private Set<UserRequest> requests = new HashSet<>();

    @ManyToOne()
    private Employee requestedBy;
    private String signiture;

    private String users;

    public RequestFormMemo() {
    }

    public RequestFormMemo(String subject, Division from, Division to, Set<Division> cc, Set<UserRequest> requests,
            Employee requestedBy, String signiture, String users) {
        this.subject = subject;
        this.from = from;
        this.to = to;
        this.cc = cc;
        this.requests = requests;
        this.requestedBy = requestedBy;
        this.signiture = signiture;
        this.users = users;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Division getFrom() {
        return this.from;
    }

    public void setFrom(Division from) {
        this.from = from;
    }

    public Division getTo() {
        return this.to;
    }

    public void setTo(Division to) {
        this.to = to;
    }

    public Set<Division> getCc() {
        return this.cc;
    }

    public void setCc(Set<Division> cc) {
        this.cc = cc;
    }

    public Set<UserRequest> getRequests() {
        return this.requests;
    }

    public void setRequests(Set<UserRequest> requests) {
        this.requests = requests;
    }

    public Employee getRequestedBy() {
        return this.requestedBy;
    }

    public void setRequestedBy(Employee requestedBy) {
        this.requestedBy = requestedBy;
    }

    public String getSigniture() {
        return this.signiture;
    }

    public void setSigniture(String signiture) {
        this.signiture = signiture;
    }

    public String getUsers() {
        return this.users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

}
