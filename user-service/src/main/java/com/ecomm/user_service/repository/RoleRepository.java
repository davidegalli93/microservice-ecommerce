package com.ecomm.user_service.repository;

import com.ecomm.user_service.model.entity.RoleEntity;
import com.ecomm.user_service.model.entity.RoleName;
import com.ecomm.user_service.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Enumeration;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByRoleName(RoleName name);
}
