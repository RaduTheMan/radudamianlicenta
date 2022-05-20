package com.damian.gemixqueapi.controller;

import com.damian.gemixqueapi.dto.UserRequest;
import com.damian.gemixqueapi.entity.UserEntity;
import com.damian.gemixqueapi.exception.ResourceNotFoundException;
import com.damian.gemixqueapi.projection.user.GetFollowersInterfaceProjection;
import com.damian.gemixqueapi.projection.user.GetFollowingInterfaceProjection;
import com.damian.gemixqueapi.projection.user.UserInterfaceProjection;
import com.damian.gemixqueapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<UserInterfaceProjection> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public UserInterfaceProjection getUser(@PathVariable String id){
        return userService.getUserById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @GetMapping("/users/{id}/followers")
    public GetFollowersInterfaceProjection getFollowersOfUser(@PathVariable String id){
        return userService.getFollowersOfUser(id).orElseThrow(ResourceNotFoundException::new);
    }

    @GetMapping("/users/{id}/following")
    public GetFollowingInterfaceProjection getFollowingOfUser(@PathVariable String id){
        return userService.getFollowingOfUser(id).orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping("/users")
    public void createUser(@RequestBody @Valid UserRequest userRequest){
        System.out.println(userRequest);
    }
}
