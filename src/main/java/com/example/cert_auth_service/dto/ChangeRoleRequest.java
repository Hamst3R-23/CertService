package com.example.cert_auth_service.dto;

public class ChangeRoleRequest {
    long userId;
    long roleId;

    public ChangeRoleRequest() {
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getRoleId() {
        return this.roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}
