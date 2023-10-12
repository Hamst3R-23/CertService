package com.example.cert_auth_service.dto;

public class AuthModel {
    String certificate;

    public AuthModel(String certificate) {
        this.certificate = certificate;
    }

    public String getCertificate() {
        return this.certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }
}

