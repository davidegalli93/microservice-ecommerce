package com.ecomm.userservice.service;

import com.ecomm.userservice.model.dto.UserDTO;
import com.ecomm.userservice.model.entity.RoleEntity;
import com.ecomm.userservice.model.entity.RoleName;
import com.ecomm.userservice.model.entity.UserEntity;
import com.ecomm.userservice.repository.RoleRepository;
import com.ecomm.userservice.repository.UserRepository;
import com.ecomm.userservice.utils.converter.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserConverter userConverter;


    public UserDTO getById (Long id){
        if (userRepo.findById(id).isPresent()){
            UserEntity user = userRepo.findById(id).get();
            return userConverter.toDto(user);
        } else throw new NullPointerException("Utente non esistente!");
    }

    public List<UserDTO> getAllUser() {
        List<UserDTO> listUsers = new ArrayList<>();
        if(!userRepo.findAll().isEmpty()){
            List<UserEntity> users = userRepo.findAll();
            for (UserEntity  user : users){
                listUsers.add(userConverter.toDto(user));
            }
        } else throw new NullPointerException("Lista utenti vuota!");
        return listUsers;
    }

    public boolean saveUser (UserDTO userDTO){
        UserEntity user = userRepo.findByEmail(userDTO.getMailUser());
        RoleEntity role = roleRepository.findByRoleName(RoleName.valueOf("USER"));
        if (user == null){
            UserEntity user1 = new UserEntity(userDTO.getMailUser(), userDTO.getPasswordUser(), role);
            userRepo.save(user1);
            return true;
        } else throw new IllegalArgumentException("Utente già presente con mail :  " + user.getEmail());
    }

    public boolean updateUser (UserDTO userDTO){
        UserEntity user = userRepo.findByEmail(userDTO.getMailUser());
        RoleEntity role = roleRepository.findByRoleName(RoleName.valueOf(userDTO.getRoleUser()));
        user.setEmail(userDTO.getMailUser());
        user.setPassword(userDTO.getPasswordUser());
        user.setRole(role);
        userRepo.save(user);
        return true;
    }

    public boolean deleteUser (Long id){
        if (userRepo.findById(id).isPresent()){
            userRepo.deleteById(id);
            return true;
        } else throw new NullPointerException("Utente non esistente");
    }
}
