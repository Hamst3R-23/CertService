package com.example.cert_auth_service.parser;

import com.example.cert_auth_service.model.service.CertificateModelService;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;

public class CertificateParser {

    private CertificateParser() {
    }

    private static final String SHA_256 = "SHA-256";

    public static CertificateModelService Parser(String string) throws CertificateException, IOException {

        String toDecode = string
                .replace("-----BEGIN CERTIFICATE-----", "")
                .replaceAll(System.lineSeparator(), "")
                .replace("-----END CERTIFICATE-----", "");

        ByteArrayInputStream certStream = new ByteArrayInputStream(Base64.getDecoder().decode(toDecode));
        CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
        X509Certificate cert = (X509Certificate) certFactory.generateCertificate(certStream);
        String fingerprint = DigestUtils.sha256Hex(certStream);

        return new CertificateModelService(fingerprint, SHA_256, cert.getSubjectX500Principal().toString());
    }

}
