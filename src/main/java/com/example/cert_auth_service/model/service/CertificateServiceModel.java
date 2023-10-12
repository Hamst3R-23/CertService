package com.example.cert_auth_service.model.service;

public class CertificateServiceModel {
    private String fingerprint;
    private String fingerprintAlgorithm;
    private String subject;

    public CertificateServiceModel(String fingerprint, String fingerprintAlgorithm, String subject) {
        this.fingerprint = fingerprint;
        this.fingerprintAlgorithm = fingerprintAlgorithm;
        this.subject = subject;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFingerprint() {
        return this.fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getFingerprintAlgorithm() {
        return this.fingerprintAlgorithm;
    }

    public void setFingerprintAlgorithm(String fingerprintAlgorithm) {
        this.fingerprintAlgorithm = fingerprintAlgorithm;
    }
}
