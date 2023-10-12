package com.example.cert_auth_service.model.controller;

public class RoleControllerModel {
    private long id;
    private String name;

    public RoleControllerModel() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
