package com.example.cert_auth_service.exception;

public class NoSuchException extends RuntimeException {

    public NoSuchException() {
    }

    public NoSuchException(String message) {
        super(message);
    }

}
