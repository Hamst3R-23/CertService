package com.example.cert_auth_service.model.repository;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinColumn(name = "role_id")
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private Set<Certificate> certificate;


    public Set<Certificate> getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        certificate.setUser(this);
        this.certificate.add(certificate);
    }

    public User() {
    }

    public User(String name) {
        this.name = name;
        this.roles = new HashSet<>();
        this.certificate = new HashSet<>();
    }

    public User(User user) {
        this.name = user.name;
        this.roles = user.roles;
        this.certificate = user.certificate;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Role role) {
        roles.add(role);
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
