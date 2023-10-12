package com.example.cert_auth_service.exception;

import com.example.cert_auth_service.model.exception.ExceptionResponse;
import java.security.cert.CertificateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvisor {
    public ExceptionControllerAdvisor() {
    }

    @ExceptionHandler({AlreadyAddedException.class})
    public ResponseEntity<ExceptionResponse> handleAlreadyAddedException(AlreadyAddedException exception) {
        return new ResponseEntity(new ExceptionResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NoSuchException.class})
    public ResponseEntity<ExceptionResponse> handleNoSuchException(NoSuchException exception) {
        return new ResponseEntity(new ExceptionResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({CertificateException.class})
    public ResponseEntity<ExceptionResponse> handleCertificateException(CertificateException exception) {
        return new ResponseEntity(new ExceptionResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}