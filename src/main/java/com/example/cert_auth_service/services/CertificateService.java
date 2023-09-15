package com.example.cert_auth_service.services;

import com.example.cert_auth_service.dto.JsonToCertificateRequest;
import com.example.cert_auth_service.exception.AlreadyAddedException;
import com.example.cert_auth_service.exception.NoSuchException;
import com.example.cert_auth_service.model.controller.CertificateControllerModel;
import com.example.cert_auth_service.model.controller.DataResponseModel;
import com.example.cert_auth_service.model.controller.UserWithAllDataModel;
import com.example.cert_auth_service.model.repository.Certificate;
import com.example.cert_auth_service.model.service.CertificateServiceModel;
import com.example.cert_auth_service.parser.CertificateParser;
import com.example.cert_auth_service.repository.CertificateRepository;
import com.example.cert_auth_service.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.cert.CertificateException;
import java.util.List;

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

        if (certificateRepository.existsByFingerprint(certificateServiceModel.getFingerprint())) {
            throw new AlreadyAddedException("This certificate is already added!");
        }

        Certificate certificate = new Certificate(certificateServiceModel.getFingerprint(), certificateServiceModel.getFingerprintAlgorithm(), certificateServiceModel.getSubject());

        certificateRepository.save(certificate);
    }

    @Transactional
    public void deleteCertificate(CertificateControllerModel certificateControllerModel) {
        certificateRepository.findById(certificateControllerModel.getId()).orElseThrow(() -> new NoSuchException("No such certificate in database"));

        certificateRepository.deleteById(certificateControllerModel.getId());
    }

    public UserWithAllDataModel checkCertificate(JsonToCertificateRequest certificateRequest) throws CertificateException {

        String toParse = certificateRequest.getCertificate();
        CertificateServiceModel certificateServiceModel = CertificateParser.parse(toParse);

        Certificate certificate = certificateRepository.findByFingerprint(certificateServiceModel.getFingerprint()).orElseThrow(() -> new NoSuchException("No such certificate in database"));

        return new UserWithAllDataModel(userRepository.findUserByCertificate(certificate.getFingerprint()).get());
    }

    public List<Certificate> findAll() {
        return new DataResponseModel<>(certificateRepository.findAll()).getList();
    }

}
