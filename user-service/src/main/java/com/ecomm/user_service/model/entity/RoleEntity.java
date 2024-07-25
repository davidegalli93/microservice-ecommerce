package com.ecomm.user_service.model.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRole;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleName roleName;

    public RoleEntity(RoleName roleName) {
        this.roleName = roleName;
    }

    public RoleEntity() {}

    public long getIdUserRole() {
        return idRole;
    }

    public void setIdUserRole(long idUserRole) {
        this.idRole = idUserRole;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity role = (RoleEntity) o;
        return idRole == role.idRole && roleName == role.roleName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRole, roleName);
    }

    @Override
    public String toString() {
        return roleName.toString() ;
    }
}
