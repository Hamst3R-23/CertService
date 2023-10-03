package com.example.cert_auth_service.services;

import com.example.cert_auth_service.dto.AuthModel;
import com.example.cert_auth_service.dto.JsonToCertificateRequest;
import com.example.cert_auth_service.exception.AlreadyAddedException;
import com.example.cert_auth_service.exception.NoSuchException;
import com.example.cert_auth_service.model.controller.CertificateControllerModel;
import com.example.cert_auth_service.model.controller.DataResponseModel;
import com.example.cert_auth_service.model.controller.UserWithAllDataModel;
import com.example.cert_auth_service.model.repository.Certificate;
import com.example.cert_auth_service.model.repository.User;
import com.example.cert_auth_service.model.service.CertificateServiceModel;
import com.example.cert_auth_service.parser.CertificateParser;
import com.example.cert_auth_service.repository.CertificateRepository;
import com.example.cert_auth_service.repository.UserRepository;
import java.security.cert.CertificateException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CertificateService {
    private final CertificateRepository certificateRepository;
    private final UserRepository userRepository;

    public CertificateService(CertificateRepository certificateRepository, UserRepository userRepository) {
        this.certificateRepository = certificateRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void addCertificate(JsonToCertificateRequest certificateRequest) throws CertificateException {
        String toParse = certificateRequest.getCertificate();
        CertificateServiceModel certificateServiceModel = CertificateParser.parse(toParse);
        if (this.certificateRepository.existsByFingerprint(certificateServiceModel.getFingerprint())) {
            throw new AlreadyAddedException("This certificate is already added!");
        } else {
            Certificate certificate = new Certificate(certificateServiceModel.getFingerprint(), certificateServiceModel.getFingerprintAlgorithm(), certificateServiceModel.getSubject());
            this.certificateRepository.save(certificate);
        }
    }

    @Transactional
    public void deleteCertificate(CertificateControllerModel certificateControllerModel) {
        this.certificateRepository.findById(certificateControllerModel.getId()).orElseThrow(() -> {
            return new NoSuchException("No such certificate in database");
        });
        this.certificateRepository.deleteById(certificateControllerModel.getId());
    }

    public UserWithAllDataModel checkCertificate(JsonToCertificateRequest certificateRequest) throws CertificateException {
        String toParse = certificateRequest.getCertificate();
        CertificateServiceModel certificateServiceModel = CertificateParser.parse(toParse);
        Certificate certificate = (Certificate)this.certificateRepository.findByFingerprint(certificateServiceModel.getFingerprint()).orElseThrow(() -> {
            return new NoSuchException("No such certificate in database");
        });
        return new UserWithAllDataModel((User)this.userRepository.findUserByCertificate(certificate.getFingerprint()).get());
    }

    public UserWithAllDataModel checkCertificate(AuthModel certificateRequest) throws CertificateException {
        String toParse = certificateRequest.getCertificate();
        CertificateServiceModel certificateServiceModel = CertificateParser.parse(toParse);
        Certificate certificate = (Certificate)this.certificateRepository.findByFingerprint(certificateServiceModel.getFingerprint()).orElseThrow(() -> {
            return new NoSuchException("No such certificate in database");
        });
        return new UserWithAllDataModel((User)this.userRepository.findUserByCertificate(certificate.getFingerprint()).get());
    }

    public List<Certificate> findAll() {
        return (new DataResponseModel(this.certificateRepository.findAll())).getList();
    }
}