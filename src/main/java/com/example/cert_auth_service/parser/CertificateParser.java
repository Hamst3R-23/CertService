package com.example.cert_auth_service.parser;

import com.example.cert_auth_service.model.service.CertificateServiceModel;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;


public class CertificateParser {

    private CertificateParser() {
    }

    private static final String SHA_256 = "SHA-256";
    private static final String X509 = "X.509";

    public static CertificateServiceModel parse(String certificate) throws CertificateException {
        InputStream targetStream = new ByteArrayInputStream(certificate.getBytes());

        X509Certificate cert = (X509Certificate) CertificateFactory
                .getInstance(X509)
                .generateCertificate(targetStream);


        String toDecode = certificate
                .replace("-----BEGIN CERTIFICATE-----", "")
                .replaceAll(System.lineSeparator(), "")
                .replace("-----END CERTIFICATE-----", "");
        String fingerprint = DigestUtils.sha256Hex(Base64.getDecoder().decode(toDecode));

        return new CertificateServiceModel(fingerprint, SHA_256, cert.getSubjectX500Principal().toString());
    }

}
