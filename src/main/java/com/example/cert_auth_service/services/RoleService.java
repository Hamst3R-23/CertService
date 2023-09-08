package com.example.cert_auth_service.services;

import com.example.cert_auth_service.model.controller.RoleModelController;
import com.example.cert_auth_service.model.repository.Role;
import com.example.cert_auth_service.model.service.RoleModelService;
import com.example.cert_auth_service.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void addRole(RoleModelController roleModelController) {
        RoleModelService roleModelService = new RoleModelService(roleModelController.getName());
        Role role = new Role(roleModelService.getName());
        roleRepository.save(role);
    }

    @Transactional
    public void deleteRoleByName(RoleModelController roleModelController) {

        RoleModelService roleModelService = new RoleModelService(roleModelController.getName());
        roleRepository.deleteByName(roleModelService.getName());
    }

    @Transactional
    public void deleteRoleById(RoleModelController roleModelController) {
        roleRepository.deleteById(roleModelController.getId());
    }

    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }

}
