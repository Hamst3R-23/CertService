package com.example.cert_auth_service.repository;

import com.example.cert_auth_service.model.repository.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    void deleteByName(String name);

    Role findById(long id);

}
