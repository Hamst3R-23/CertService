package com.example.cert_auth_service.repository;

import com.example.cert_auth_service.model.repository.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    void deleteByName(String userName);

    Optional<User> findById(long id);

    Optional<User> findByName(String name);

    @Query("select u from User u inner join u.certificate certificate where certificate.fingerprint = ?1")
    Optional<User> findUserByCertificate(String fingerprint);

}
