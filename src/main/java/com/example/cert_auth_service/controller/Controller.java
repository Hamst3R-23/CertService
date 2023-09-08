package com.example.cert_auth_service.controller;

import com.example.cert_auth_service.dto.ChangeCertificateRequest;
import com.example.cert_auth_service.dto.ChangeRoleRequest;
import com.example.cert_auth_service.model.controller.CertificateModelController;
import com.example.cert_auth_service.model.controller.RoleModelController;
import com.example.cert_auth_service.model.controller.UserModelController;
import com.example.cert_auth_service.services.CertificateService;
import com.example.cert_auth_service.services.RoleService;
import com.example.cert_auth_service.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.cert.CertificateException;

@RestController
public class Controller {

    private final UserService userService;

    private final RoleService roleService;

    private final CertificateService certificateService;

    public Controller(UserService userService, RoleService roleService, CertificateService certificateService) {
        this.userService = userService;
        this.roleService = roleService;
        this.certificateService = certificateService;
    }

    @PostMapping("/user/addition")
    public ResponseEntity<?> addUser(
            @RequestBody UserModelController userModelController
    ) {
        userService.addUser(userModelController);
        return new ResponseEntity<>(userService.findAll(), HttpStatus.CREATED);
    }

    @PostMapping("/user/deletion")
    public ResponseEntity<?> deleteUser(
            @RequestBody UserModelController userModelController
    ) {
        if (userModelController.getName() == null || userModelController.getName().trim().isEmpty()) {
            userService.deleteUserById(userModelController);
            return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
        } else {
            userService.deleteUserByName(userModelController);
            return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
        }
    }

    @PostMapping("/user/role/addition")
    public ResponseEntity<?> setRoleToUser(
            @RequestBody ChangeRoleRequest changeRoleRequest
    ) {
        userService.setRoleToUser(changeRoleRequest);
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/user/role/deletion")
    public ResponseEntity<?> deleteRoleFromUser(
            @RequestBody ChangeRoleRequest changeRoleRequest
    ) {
        userService.deleteRoleFromUser(changeRoleRequest);
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/role/addition")
    public ResponseEntity<?> addRole(
            @RequestBody RoleModelController roleModelController
    ) {
        roleService.addRole(roleModelController);
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.CREATED);
    }

    @PostMapping("/role/deletion")
    public ResponseEntity<?> deleteRole(
            @RequestBody RoleModelController roleModelController
    ) {
        if (roleModelController.getName() == null || roleModelController.getName().trim().isEmpty()) {
            roleService.deleteRoleById(roleModelController);
            return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
        } else {
            roleService.deleteRoleByName(roleModelController);
            return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
        }
    }

    @PostMapping("certificate/addition")
    public ResponseEntity<?> addCertificate(
            @RequestParam("file") MultipartFile file
    ) throws CertificateException, IOException {
        certificateService.addCertificate(file);
        return new ResponseEntity<>(certificateService.findAll(), HttpStatus.CREATED);
    }

    @PostMapping("certificate/deletion")
    public ResponseEntity<?> deleteCertificate(
            @RequestBody CertificateModelController certificateModelController
    ) {
        certificateService.deleteCertificate(certificateModelController);
        return new ResponseEntity<>(certificateService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/user/certificate/addition")
    public ResponseEntity<?> setCertificateToUser(
            @RequestBody ChangeCertificateRequest changeCertificateRequest
    ) {
        userService.setCertificateToUser(changeCertificateRequest);
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/user/certificate/deletion")
    public ResponseEntity<?> deleteCertificateFromUser(
            @RequestBody ChangeCertificateRequest changeCertificateRequest
    ) {
        userService.deleteCertificateFromUser(changeCertificateRequest);
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/check")
    public ResponseEntity<?> checkForAvalibleСertificate(
            @RequestParam("file") MultipartFile file
    ) {
        try {
            return new ResponseEntity<>(certificateService.checkCertificate(file), HttpStatus.FOUND);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

}
