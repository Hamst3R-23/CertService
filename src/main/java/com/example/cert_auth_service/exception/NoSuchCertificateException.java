package com.example.cert_auth_service.exception;

public class NoSuchCertificateException extends RuntimeException {

    public NoSuchCertificateException() {
    }

    public NoSuchCertificateException(String message) {
        super(message);
    }

}
