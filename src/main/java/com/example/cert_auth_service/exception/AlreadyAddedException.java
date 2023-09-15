package com.example.cert_auth_service.exception;

public class AlreadyAddedException extends RuntimeException {

    public AlreadyAddedException() {
    }

    public AlreadyAddedException(String message) {
        super(message);
    }

}
