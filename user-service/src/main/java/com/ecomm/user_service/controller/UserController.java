package com.ecomm.user_service.controller;

import com.ecomm.user_service.model.dto.UserDTO;
import com.ecomm.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public UserDTO findByID (@PathVariable Long id){
        return userService.getById(id);
    }

    @GetMapping
    public List<UserDTO> findAll (){
        return userService.getAllUser();
    }

    @PostMapping
    public boolean create (@RequestBody UserDTO userDTO){
        return userService.saveUser(userDTO);
    }

    @PutMapping
    public boolean update (@RequestBody UserDTO userDTO){
        return userService.updateUser(userDTO);
    }

    @DeleteMapping("/{id}")
    public boolean delete (@PathVariable Long id){
        return userService.deleteUser(id);
    }

}
