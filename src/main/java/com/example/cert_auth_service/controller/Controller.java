package com.example.cert_auth_service.controller;

import com.example.cert_auth_service.dto.ChangeCertificateRequest;
import com.example.cert_auth_service.dto.ChangeRoleRequest;
import com.example.cert_auth_service.dto.JsonToCertificateRequest;
import com.example.cert_auth_service.model.controller.CertificateControllerModel;
import com.example.cert_auth_service.model.controller.JsonResponseToCreateDeleteOperationsModel;
import com.example.cert_auth_service.model.controller.RoleControllerModel;
import com.example.cert_auth_service.model.controller.UserControllerModel;
import com.example.cert_auth_service.model.repository.Certificate;
import com.example.cert_auth_service.model.repository.Role;
import com.example.cert_auth_service.model.repository.User;
import com.example.cert_auth_service.services.CertificateService;
import com.example.cert_auth_service.services.RoleService;
import com.example.cert_auth_service.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.cert.CertificateException;
import java.util.List;

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
    public ResponseEntity<JsonResponseToCreateDeleteOperationsModel> addUser(
            @RequestBody UserControllerModel userControllerModel
    ) {
        userService.addUser(userControllerModel);
        return new ResponseEntity<>(new JsonResponseToCreateDeleteOperationsModel("Account successfully added to DataBase!"), HttpStatus.CREATED);
    }

    @DeleteMapping("/user/deletion")
    public ResponseEntity<JsonResponseToCreateDeleteOperationsModel> deleteUser(
            @RequestBody UserControllerModel userControllerModel
    ) {
        if (userControllerModel.getName() == null || userControllerModel.getName().trim().isEmpty()) {
            userService.deleteUserById(userControllerModel);
            return new ResponseEntity<>(new JsonResponseToCreateDeleteOperationsModel("Account successfully deleted by Id!"), HttpStatus.OK);
        } else {
            userService.deleteUserByName(userControllerModel);
            return new ResponseEntity<>(new JsonResponseToCreateDeleteOperationsModel("Account successfully deleted by Name!"), HttpStatus.OK);
        }
    }

    @PostMapping("/user/role/addition")
    public ResponseEntity<JsonResponseToCreateDeleteOperationsModel> setRoleToUser(
            @RequestBody ChangeRoleRequest changeRoleRequest
    ) {
        userService.setRoleToUser(changeRoleRequest);
        return new ResponseEntity<>(new JsonResponseToCreateDeleteOperationsModel("Role successfully added to User!"), HttpStatus.OK);
    }

    @DeleteMapping("/user/role/deletion")
    public ResponseEntity<JsonResponseToCreateDeleteOperationsModel> deleteRoleFromUser(
            @RequestBody ChangeRoleRequest changeRoleRequest
    ) {
        userService.deleteRoleFromUser(changeRoleRequest);
        return new ResponseEntity<>(new JsonResponseToCreateDeleteOperationsModel("Role successfully deleted from User by RoleName!"), HttpStatus.OK);
    }

    @GetMapping("/user/info")
    public ResponseEntity<List<User>> findAllUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/role/addition")
    public ResponseEntity<JsonResponseToCreateDeleteOperationsModel> addRole(
            @RequestBody RoleControllerModel roleControllerModel
    ) {
        roleService.addRole(roleControllerModel);
        return new ResponseEntity<>(new JsonResponseToCreateDeleteOperationsModel("Role successfully added to DataBase!"), HttpStatus.CREATED);
    }

    @DeleteMapping("/role/deletion")
    public ResponseEntity<JsonResponseToCreateDeleteOperationsModel> deleteRole(
            @RequestBody RoleControllerModel roleControllerModel
    ) {
        if (roleControllerModel.getName() == null || roleControllerModel.getName().trim().isEmpty()) {
            roleService.deleteRoleById(roleControllerModel);
            return new ResponseEntity<>(new JsonResponseToCreateDeleteOperationsModel("Role successfully deleted from DataBase by Id!"), HttpStatus.OK);
        } else {
            roleService.deleteRoleByName(roleControllerModel);
            return new ResponseEntity<>(new JsonResponseToCreateDeleteOperationsModel("Role successfully deleted from DataBase by Name"), HttpStatus.OK);
        }
    }

    @GetMapping("role/info")
    public ResponseEntity<List<Role>> findAllRoles() {
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }

    @PostMapping("certificate/addition")
    public ResponseEntity<JsonResponseToCreateDeleteOperationsModel> addCertificate(
            @RequestBody JsonToCertificateRequest jsonToCertificateRequest
    ) throws CertificateException {
        certificateService.addCertificate(jsonToCertificateRequest);
        return new ResponseEntity<>(new JsonResponseToCreateDeleteOperationsModel("Certificate successfully added to DataBase!"), HttpStatus.CREATED);
    }

    @DeleteMapping("certificate/deletion")
    public ResponseEntity<JsonResponseToCreateDeleteOperationsModel> deleteCertificate(
            @RequestBody CertificateControllerModel certificateControllerModel
    ) {
        certificateService.deleteCertificate(certificateControllerModel);
        return new ResponseEntity<>(new JsonResponseToCreateDeleteOperationsModel("Certificate successfully deleted from DataBase!"), HttpStatus.OK);
    }

    @GetMapping("certificate/info")
    public ResponseEntity<List<Certificate>> findAllCertificates() {
        return new ResponseEntity<>(certificateService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/user/certificate/addition")
    public ResponseEntity<JsonResponseToCreateDeleteOperationsModel> setCertificateToUser(
            @RequestBody ChangeCertificateRequest changeCertificateRequest
    ) {
        userService.setCertificateToUser(changeCertificateRequest);
        return new ResponseEntity<>(new JsonResponseToCreateDeleteOperationsModel("Certificate successfully added to User!"), HttpStatus.OK);
    }

    @DeleteMapping("/user/certificate/deletion")
    public ResponseEntity<JsonResponseToCreateDeleteOperationsModel> deleteCertificateFromUser(
            @RequestBody ChangeCertificateRequest changeCertificateRequest
    ) {
        userService.deleteCertificateFromUser(changeCertificateRequest);
        return new ResponseEntity<>(new JsonResponseToCreateDeleteOperationsModel("Certificate successfully deleted from User!"), HttpStatus.OK);
    }

    @GetMapping("/check")
    public ResponseEntity<?> checkForAvailableCertificate(
            @RequestBody JsonToCertificateRequest jsonToCertificateRequest
    ) {
        try {
            return new ResponseEntity<>(certificateService.checkCertificate(jsonToCertificateRequest), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

}
