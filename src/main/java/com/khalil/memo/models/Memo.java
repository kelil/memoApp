package com.khalil.memo.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table()
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String subject;
    @ManyToOne()
    private Division from;
    @ManyToOne()
    private Division to;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "memo_division", joinColumns = { @JoinColumn(name = "memo_id") }, inverseJoinColumns = {
            @JoinColumn(name = "division_id") })
    private Set<Division> cc = new HashSet<>();

    @Column(columnDefinition = "TEXT")
    private String body;

    

    private String users;
    @ManyToOne()
    private Employee requestedBy;
    private String signiture;

    public Memo(String name, String subject, Division from, Division to, Set<Division> cc, String body, String users,
            Employee requestedBy, String signiture) {
        this.name = name;
        this.subject = subject;
        this.from = from;
        this.to = to;
        this.cc = cc;
        this.body = body;
        this.users = users;
        this.requestedBy = requestedBy;
        this.signiture = signiture;
    }

    public Memo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Division getFrom() {
        return from;
    }

    public void setFrom(Division from) {
        this.from = from;
    }

    public Division getTo() {
        return to;
    }

    public void setTo(Division to) {
        this.to = to;
    }

    public Set<Division> getCc() {
        return cc;
    }

    public void setCc(Set<Division> cc) {
        this.cc = cc;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUser() {
        return users;
    }

    public void setUser(String users) {
        this.users = users;
    }

    public Employee getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(Employee requestedBy) {
        this.requestedBy = requestedBy;
    }

    public String getSigniture() {
        return signiture;
    }

    public void setSigniture(String signiture) {
        this.signiture = signiture;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", subject='" + getSubject() + "'" +
                ", from='" + getFrom() + "'" +
                ", to='" + getTo() + "'" +
                ", cc='" + getCc() + "'" +
                ", user='" + getUser() + "'" +
                ", requestedBy='" + getRequestedBy() + "'" +
                ", signiture='" + getSigniture() + "'" +
                "}";
    }

}
