package com.example.cert_auth_service.model.controller;

import com.example.cert_auth_service.model.repository.Certificate;
import com.example.cert_auth_service.model.repository.Role;
import com.example.cert_auth_service.model.repository.User;

import java.util.Set;

public class UserWithAllDataModel {

    private String name;

    private Set<Role> roles;

    private Set<Certificate> certificate;

    public UserWithAllDataModel(User user) {
        this.name = user.getName();
        this.roles = user.getRoles();
        this.certificate = user.getCertificate();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Certificate> getCertificate() {
        return certificate;
    }

    public void setCertificate(Set<Certificate> certificate) {
        this.certificate = certificate;
    }

}
