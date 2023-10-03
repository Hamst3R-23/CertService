package com.example.cert_auth_service.model.controller;

public class CertificateControllerModel {
    private long id;
    private String fingerprint;
    private String fingerprintAlgorithm;
    private String subject;

    public CertificateControllerModel() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
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
