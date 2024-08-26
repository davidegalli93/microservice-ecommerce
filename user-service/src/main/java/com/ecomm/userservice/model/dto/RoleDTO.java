package com.ecomm.userservice.model.dto;

public class RoleDTO {

    private Long roleId;
    private String roleName;

    public RoleDTO(String roleName) {
        this.roleName = roleName;
    }
}
