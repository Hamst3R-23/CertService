package com.example.cert_auth_service.services;

import com.example.cert_auth_service.exception.AlreadyAddedException;
import com.example.cert_auth_service.exception.NoSuchException;
import com.example.cert_auth_service.model.controller.DataResponseModel;
import com.example.cert_auth_service.model.controller.RoleControllerModel;
import com.example.cert_auth_service.model.repository.Role;
import com.example.cert_auth_service.model.service.RoleServiceModel;
import com.example.cert_auth_service.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public void addRole(RoleControllerModel roleControllerModel) {
        if (roleRepository.findByName(roleControllerModel.getName()).isPresent()) {
            throw new AlreadyAddedException("This role is already added!");
        }

        RoleServiceModel roleServiceModel = new RoleServiceModel(roleControllerModel.getName());

        Role role = new Role(roleServiceModel.getName());
        roleRepository.save(role);
    }

    @Transactional
    public void deleteRoleByName(RoleControllerModel roleControllerModel) {
        roleRepository.findById(roleControllerModel.getId()).orElseThrow(() -> new NoSuchException("No such role in database"));

        RoleServiceModel roleServiceModel = new RoleServiceModel(roleControllerModel.getName());
        roleRepository.deleteByName(roleServiceModel.getName());
    }

    @Transactional
    public void deleteRoleById(RoleControllerModel roleControllerModel) {
        roleRepository.findById(roleControllerModel.getId()).orElseThrow(() -> new NoSuchException("No such role in database"));
        roleRepository.deleteById(roleControllerModel.getId());
    }

    public List<Role> findAll() {
        return new DataResponseModel<>(roleRepository.findAll()).getList();
    }

}
