package com.example.cert_auth_service.dto;

public class JsonToCertificateRequest {
    private String certificate;

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String json) {
        this.certificate = json;
    }
}
