package com.example.cert_auth_service.model.service;

public class UserModelService {

    private String name;

    public UserModelService() {
    }

    public UserModelService(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
