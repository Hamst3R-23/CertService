package com.example.cert_auth_service.repository;

import com.example.cert_auth_service.model.repository.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    void deleteByName(String name);

    Optional<Role> findById(long id);

    Optional<Role> findByName(String name);

}
