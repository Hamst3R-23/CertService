package com.example.cert_auth_service.services;

import com.example.cert_auth_service.dto.ChangeCertificateRequest;
import com.example.cert_auth_service.dto.ChangeRoleRequest;
import com.example.cert_auth_service.model.controller.UserModelController;
import com.example.cert_auth_service.model.repository.Certificate;
import com.example.cert_auth_service.model.repository.Role;
import com.example.cert_auth_service.model.repository.User;
import com.example.cert_auth_service.model.service.UserModelService;
import com.example.cert_auth_service.repository.CertificateRepository;
import com.example.cert_auth_service.repository.RoleRepository;
import com.example.cert_auth_service.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final CertificateRepository certificateRepository;


    public UserService(UserRepository userRepository, RoleRepository roleRepository, CertificateRepository certificateRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.certificateRepository = certificateRepository;
    }

    public void addUser(UserModelController userModelController) {
        UserModelService userModelService = new UserModelService(userModelController.getName());
        User user = new User(userModelService.getName());
        userRepository.save(user);
    }

    @Transactional
    public void deleteUserByName(UserModelController userModelController) {
        UserModelService userModelService = new UserModelService(userModelController.getName());
        userRepository.deleteByName(userModelService.getName());
    }

    @Transactional
    public void deleteUserById(UserModelController userModelController) {
        userRepository.deleteById(userModelController.getId());
    }

    public void setRoleToUser(ChangeRoleRequest changeRoleRequest) {
        User user = userRepository.findById(changeRoleRequest.getUserId());
        Role role = roleRepository.findById(changeRoleRequest.getRoleId());
        user.setRoles(role);
        userRepository.save(user);
    }

    public void deleteRoleFromUser(ChangeRoleRequest changeRoleRequest) {
        User user = userRepository.findById(changeRoleRequest.getUserId());
        Role role = roleRepository.findById(changeRoleRequest.getRoleId());
        user.getRoles().remove(role);
        userRepository.save(user);
    }

    public void setCertificateToUser(ChangeCertificateRequest changeCertificateRequest) {
        User user = userRepository.findById(changeCertificateRequest.getUserId());
        Certificate certificate = certificateRepository.findById(changeCertificateRequest.getCertificateId());
        user.setCertificate(certificate);
        userRepository.save(user);
    }

    public void deleteCertificateFromUser(ChangeCertificateRequest changeCertificateRequest) {
        User user = userRepository.findById(changeCertificateRequest.getUserId());
        Certificate certificate = certificateRepository.findById(changeCertificateRequest.getCertificateId());
        user.getCertificate().remove(certificate);
        userRepository.save(user);
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

}