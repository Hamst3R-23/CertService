package com.example.cert_auth_service.dto;

public class JsonToCertificateRequest {
    private String certificate;

    public JsonToCertificateRequest() {
    }

    public String getCertificate() {
        return this.certificate;
    }

    public void setCertificate(String json) {
        this.certificate = json;
    }
}
