package com.example.cert_auth_service.model.controller;

public class UserControllerModel {
    private long id;
    private String name;

    public UserControllerModel() {
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