package com.example.cert_auth_service.parser;

import com.example.cert_auth_service.model.service.CertificateServiceModel;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class CertificateParser {
    private static final String SHA_256 = "SHA-256";
    private static final String X509 = "X.509";
    private static final String SEPARATOR = System.lineSeparator();
    private static final String BEGIN_CERTIFICATE = "-----BEGIN CERTIFICATE-----";
    private static final String END_CERTIFICATE = "-----END CERTIFICATE-----";

    private CertificateParser() {
    }

    public static CertificateServiceModel parse(String toCertificate) throws CertificateException {
        String certificate = editCertificateToSystemSeparator(toCertificate);
        InputStream targetStream = new ByteArrayInputStream(certificate.getBytes());
        X509Certificate cert = (X509Certificate)CertificateFactory.getInstance(X509).generateCertificate(targetStream);
        String toDecode = certificate
                .replace(BEGIN_CERTIFICATE, "")
                .replaceAll(SEPARATOR, "")
                .replace(END_CERTIFICATE, "");

        String fingerprint = DigestUtils.sha256Hex(Base64.getDecoder().decode(toDecode));
        return new CertificateServiceModel(fingerprint, SHA_256, cert.getSubjectX500Principal().toString());
    }

    private static String editCertificateToSystemSeparator(String certificate) {
        String begin = BEGIN_CERTIFICATE + SEPARATOR;
        String end = SEPARATOR + END_CERTIFICATE;
        certificate = certificate
                .replaceAll(BEGIN_CERTIFICATE, "")
                .replaceAll(END_CERTIFICATE, "");
        String result = "";

        for(int k = 1; k <= certificate.length() / 64; ++k) {
            String var10001 = certificate.substring(0, 64);
            result = result.concat(var10001 + SEPARATOR);
            certificate = certificate.substring(64);
        }

        return begin + result + certificate + end;
    }
}
