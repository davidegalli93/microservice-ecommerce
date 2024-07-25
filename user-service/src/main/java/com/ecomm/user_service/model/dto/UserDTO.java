package com.ecomm.user_service.model.dto;

public class UserDTO {

    private Long userId;
    private String mailUser;
    private String passwordUser;
    private String roleUser;

    //per creare e moificare lo user
    public UserDTO(String mailUser, String passwordUser) {
        this.mailUser = mailUser;
        this.passwordUser = passwordUser;
    }

    public UserDTO() {}

    public Long getUserId() {
        return userId;
    }

    public String getMailUser() {
        return mailUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public String getRoleUser() {
        return roleUser;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setMailUser(String mailUser) {
        this.mailUser = mailUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    public void setRoleUser(String roleUser) {
        this.roleUser = roleUser;
    }

}
