package com.example.cert_auth_service.model.service;

public class CertificateModelService {

    private String fingerprint;

    private String fingerprintAlgorithm;

    private String subject;


    public CertificateModelService(String fingerprint, String fingerprintAlgorithm, String subject) {
        this.fingerprint = fingerprint;
        this.fingerprintAlgorithm = fingerprintAlgorithm;
        this.subject = subject;
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
