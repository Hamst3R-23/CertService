package com.example.cert_auth_service.services;

import com.example.cert_auth_service.exception.NoSuchCertificateException;
import com.example.cert_auth_service.model.controller.CertificateModelController;
import com.example.cert_auth_service.model.repository.Certificate;
import com.example.cert_auth_service.model.repository.User;
import com.example.cert_auth_service.model.service.CertificateModelService;
import com.example.cert_auth_service.parser.CertificateParser;
import com.example.cert_auth_service.repository.CertificateRepository;
import com.example.cert_auth_service.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;

@Service
public class CertificateService {

    private final CertificateRepository certificateRepository;

    private final UserRepository userRepository;

    public CertificateService(CertificateRepository certificateRepository, UserRepository userRepository) {
        this.certificateRepository = certificateRepository;
        this.userRepository = userRepository;
    }

    public void addCertificate(MultipartFile file) throws IOException, CertificateException {

        String toParse = new String(file.getBytes(), Charset.defaultCharset());
        CertificateModelService certificateModelService = CertificateParser.Parser(toParse);

        Certificate certificate = new Certificate();
        certificate.setFingerprint(certificateModelService.getFingerprint());
        certificate.setFingerprintAlgorithm(certificateModelService.getFingerprintAlgorithm());
        certificate.setSubject(certificateModelService.getSubject());

        certificateRepository.save(certificate);
    }

    public void deleteCertificate(CertificateModelController certificateModelController) {
        certificateRepository.deleteById(certificateModelController.getId());
    }

    public User checkCertificate(MultipartFile file) throws IOException, CertificateException {

        String toParse = new String(file.getBytes(), Charset.defaultCharset());
        CertificateModelService certificateModelService = CertificateParser.Parser(toParse);

        if (certificateRepository.existsByFingerprint(certificateModelService.getFingerprint())) {
            Certificate certificate = certificateRepository.findByFingerprint(certificateModelService.getFingerprint());
            return userRepository.findUserByCertificate(certificate);
        } else {
            throw new NoSuchCertificateException("No such certificate in database");
        }
    }

    public Iterable<Certificate> findAll() {
        return certificateRepository.findAll();
    }

}
