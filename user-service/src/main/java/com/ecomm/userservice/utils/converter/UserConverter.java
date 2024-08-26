package com.ecomm.userservice.utils.converter;

import com.ecomm.userservice.model.dto.UserDTO;
import com.ecomm.userservice.model.entity.RoleEntity;
import com.ecomm.userservice.model.entity.RoleName;
import com.ecomm.userservice.model.entity.UserEntity;
import com.ecomm.userservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    @Autowired
    RoleRepository roleRepository;

    public UserEntity toEntity (UserDTO userDTO){
        UserEntity user = new UserEntity();
        RoleEntity role = roleRepository.findByRoleName(RoleName.valueOf(userDTO.getRoleUser()));
        if(userDTO.getUserId() != null){
            user.setId(userDTO.getUserId());
        }
        user.setEmail(userDTO.getMailUser());
        user.setPassword(userDTO.getPasswordUser());
        user.setRole(role);
        return user;
    }

    public UserDTO toDto (UserEntity userEntity){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userEntity.getId());
        userDTO.setMailUser(userEntity.getEmail());
        userDTO.setPasswordUser(userEntity.getPassword());
        userDTO.setRoleUser(userEntity.getRole().toString());
        return userDTO;
    }
}
