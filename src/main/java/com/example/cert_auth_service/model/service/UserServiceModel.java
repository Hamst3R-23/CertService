package com.example.cert_auth_service.model.service;

public class UserServiceModel {
    private String name;

    public UserServiceModel() {
    }

    public UserServiceModel(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
