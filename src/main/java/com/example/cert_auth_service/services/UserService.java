package com.example.cert_auth_service.services;

import com.example.cert_auth_service.dto.ChangeCertificateRequest;
import com.example.cert_auth_service.dto.ChangeRoleRequest;
import com.example.cert_auth_service.exception.AlreadyAddedException;
import com.example.cert_auth_service.exception.NoSuchException;
import com.example.cert_auth_service.model.controller.DataResponseModel;
import com.example.cert_auth_service.model.controller.UserControllerModel;
import com.example.cert_auth_service.model.repository.Certificate;
import com.example.cert_auth_service.model.repository.Role;
import com.example.cert_auth_service.model.repository.User;
import com.example.cert_auth_service.model.service.UserServiceModel;
import com.example.cert_auth_service.repository.CertificateRepository;
import com.example.cert_auth_service.repository.RoleRepository;
import com.example.cert_auth_service.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public void addUser(UserControllerModel userControllerModel) {
        if (userRepository.findByName(userControllerModel.getName()).isPresent()) {
            throw new AlreadyAddedException("This user is already added!");
        }

        UserServiceModel userServiceModel = new UserServiceModel(userControllerModel.getName());
        User user = new User(userServiceModel.getName());
        userRepository.save(user);
    }

    @Transactional
    public void deleteUserByName(UserControllerModel userControllerModel) {
        userRepository.deleteByName(userRepository.findByName(userControllerModel.getName()).orElseThrow(() -> new NoSuchException("No such user in database")).getName());
    }

    @Transactional
    public void deleteUserById(UserControllerModel userControllerModel) {
        userRepository.deleteById(userRepository.findById(userControllerModel.getId()).orElseThrow(() -> new NoSuchException("No such user in database")).getId());
    }

    public void setRoleToUser(ChangeRoleRequest changeRoleRequest) {
        User user = userRepository.findById(changeRoleRequest.getUserId()).orElseThrow(() -> new NoSuchException("No such user in database"));
        Role role = roleRepository.findById(changeRoleRequest.getRoleId()).orElseThrow(() -> new NoSuchException("No such role in database"));
        user.setRoles(role);
        userRepository.save(user);
    }

    public void deleteRoleFromUser(ChangeRoleRequest changeRoleRequest) {
        User user = userRepository.findById(changeRoleRequest.getUserId()).orElseThrow(() -> new NoSuchException("No such user in database"));
        Role role = roleRepository.findById(changeRoleRequest.getRoleId()).orElseThrow(() -> new NoSuchException("No such role in database"));
        user.getRoles().remove(role);
        userRepository.save(user);
    }

    public void setCertificateToUser(ChangeCertificateRequest changeCertificateRequest) {
        User user = userRepository.findById(changeCertificateRequest.getUserId()).orElseThrow(() -> new NoSuchException("No such user in database"));
        Certificate certificate = certificateRepository.findById(changeCertificateRequest.getCertificateId()).orElseThrow(() -> new NoSuchException("No such certificate in database"));
        user.setCertificate(certificate);
        userRepository.save(user);
    }

    public void deleteCertificateFromUser(ChangeCertificateRequest changeCertificateRequest) {
        User user = userRepository.findById(changeCertificateRequest.getUserId()).orElseThrow(() -> new NoSuchException("No such user in database"));
        Certificate certificate = certificateRepository.findById(changeCertificateRequest.getCertificateId()).orElseThrow(() -> new NoSuchException("No such certificate in database"));
        user.getCertificate().remove(certificate);
        userRepository.save(user);
    }

    public List<User> findAll() {
        return new DataResponseModel<>(userRepository.findAll()).getList();
    }

}