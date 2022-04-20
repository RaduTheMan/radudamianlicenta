package com.damian.gemixqueapi.controller;

import com.damian.gemixqueapi.exception.ResourceNotFoundException;
import com.damian.gemixqueapi.projection.user.GetFollowersInterfaceProjection;
import com.damian.gemixqueapi.projection.user.GetFollowingInterfaceProjection;
import com.damian.gemixqueapi.projection.user.UserInterfaceProjection;
import com.damian.gemixqueapi.repository.FollowersRepository;
import com.damian.gemixqueapi.repository.FollowingRepository;
import com.damian.gemixqueapi.repository.UserRepository;
import com.damian.gemixqueapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/users")
    public List<UserInterfaceProjection> getAllUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping("/users/{id}")
    public UserInterfaceProjection getUser(@PathVariable String id){
        return userService.getUserById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @RequestMapping("/users/{id}/followers")
    public GetFollowersInterfaceProjection getFollowersOfUser(@PathVariable String id){
        return userService.getFollowersOfUser(id).orElseThrow(ResourceNotFoundException::new);
    }

    @RequestMapping("/users/{id}/following")
    public GetFollowingInterfaceProjection getFollowingOfUser(@PathVariable String id){
        return userService.getFollowingOfUser(id).orElseThrow(ResourceNotFoundException::new);
    }
}
