package com.example.cert_auth_service.model.service;

public class RoleModelService {

    private String name;

    public RoleModelService() {
    }

    public RoleModelService(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
