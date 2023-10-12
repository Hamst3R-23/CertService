package com.example.cert_auth_service.dto;

public class ChangeCertificateRequest {
    private long userId;
    private long certificateId;

    public ChangeCertificateRequest() {
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCertificateId() {
        return this.certificateId;
    }

    public void setCertificateId(long certificateId) {
        this.certificateId = certificateId;
    }
}
