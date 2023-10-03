package com.example.cert_auth_service.model.service;

public class RoleServiceModel {
    private String name;

    public RoleServiceModel() {
    }

    public RoleServiceModel(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
