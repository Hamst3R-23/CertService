package com.example.cert_auth_service.services;

import com.example.cert_auth_service.exception.AlreadyAddedException;
import com.example.cert_auth_service.exception.NoSuchException;
import com.example.cert_auth_service.model.controller.DataResponseModel;
import com.example.cert_auth_service.model.controller.RoleControllerModel;
import com.example.cert_auth_service.model.repository.Role;
import com.example.cert_auth_service.model.service.RoleServiceModel;
import com.example.cert_auth_service.repository.RoleRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public void addRole(RoleControllerModel roleControllerModel) {
        if (this.roleRepository.findByName(roleControllerModel.getName()).isPresent()) {
            throw new AlreadyAddedException("This role is already added!");
        } else {
            RoleServiceModel roleServiceModel = new RoleServiceModel(roleControllerModel.getName());
            Role role = new Role(roleServiceModel.getName());
            this.roleRepository.save(role);
        }
    }

    @Transactional
    public void deleteRoleByName(RoleControllerModel roleControllerModel) {
        this.roleRepository.findById(roleControllerModel.getId()).orElseThrow(() -> {
            return new NoSuchException("No such role in database");
        });
        RoleServiceModel roleServiceModel = new RoleServiceModel(roleControllerModel.getName());
        this.roleRepository.deleteByName(roleServiceModel.getName());
    }

    @Transactional
    public void deleteRoleById(RoleControllerModel roleControllerModel) {
        this.roleRepository.findById(roleControllerModel.getId()).orElseThrow(() -> {
            return new NoSuchException("No such role in database");
        });
        this.roleRepository.deleteById(roleControllerModel.getId());
    }

    public List<Role> findAll() {
        return (new DataResponseModel(this.roleRepository.findAll())).getList();
    }
}
