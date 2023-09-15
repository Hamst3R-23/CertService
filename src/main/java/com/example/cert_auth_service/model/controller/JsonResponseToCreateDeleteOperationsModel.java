package com.example.cert_auth_service.model.controller;

public class JsonResponseToCreateDeleteOperationsModel {

    private String response;

    public JsonResponseToCreateDeleteOperationsModel(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}
