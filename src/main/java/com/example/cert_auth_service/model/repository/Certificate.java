package com.example.cert_auth_service.model.repository;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "certificates")
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String fingerprint;

    @Column(name = "fingerprintalgorithm", nullable = false)
    private String fingerprintAlgorithm;

    @Column(name = "subject", nullable = false)
    private String subject;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public Certificate() {
    }

    public Certificate(String fingerprint, String fingerprintAlgorithm, String subject) {
        this.fingerprint = fingerprint;
        this.fingerprintAlgorithm = fingerprintAlgorithm;
        this.subject = subject;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setFingerprintAlgorithm(String fingerprintAlgorithm) {
        this.fingerprintAlgorithm = fingerprintAlgorithm;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
