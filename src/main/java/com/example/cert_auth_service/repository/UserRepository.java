package com.example.cert_auth_service.repository;

import com.example.cert_auth_service.model.repository.Certificate;
import com.example.cert_auth_service.model.repository.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    void deleteByName(String userName);

    User findById(long id);

    User findUserByCertificate(Certificate certificate);

}
