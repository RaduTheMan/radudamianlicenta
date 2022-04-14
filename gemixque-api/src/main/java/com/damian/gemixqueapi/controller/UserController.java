package com.damian.gemixqueapi.controller;

import com.damian.gemixqueapi.entity.UserEntity;
import com.damian.gemixqueapi.exception.ResourceNotFoundException;
import com.damian.gemixqueapi.projection.UserInterfaceProjection;
import com.damian.gemixqueapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/users")
    public List<UserInterfaceProjection> getAllUsers(){
        return userRepository.findAllProjectedBy();
    }

    @RequestMapping("/users/{id}")
    public UserInterfaceProjection getUser(@PathVariable String id){
        return userRepository.findByUuid(id).orElseThrow(ResourceNotFoundException::new);
    }


}
