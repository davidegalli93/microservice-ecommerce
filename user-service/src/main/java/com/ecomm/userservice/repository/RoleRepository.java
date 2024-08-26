package com.ecomm.userservice.repository;

import com.ecomm.userservice.model.entity.RoleEntity;
import com.ecomm.userservice.model.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByRoleName(RoleName name);
}
