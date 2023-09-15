package com.example.cert_auth_service.model.controller;

public class CertificateControllerModel {

    private long id;

    private String fingerprint;

    private String fingerprintAlgorithm;

    private String subject;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getFingerprintAlgorithm() {
        return fingerprintAlgorithm;
    }

    public void setFingerprintAlgorithm(String fingerprintAlgorithm) {
        this.fingerprintAlgorithm = fingerprintAlgorithm;
    }

}
