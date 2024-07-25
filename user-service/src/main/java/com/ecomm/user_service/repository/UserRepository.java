package com.ecomm.user_service.repository;

import com.ecomm.user_service.model.dto.UserDTO;
import com.ecomm.user_service.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public UserEntity findByEmail(String email);

}
