package com.example.cert_auth_service.repository;

import com.example.cert_auth_service.model.repository.Certificate;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository extends CrudRepository<Certificate, Long> {
    Optional<Certificate> findById(long id);

    Optional<Certificate> findByFingerprint(String string);

    boolean existsByFingerprint(String string);
}
