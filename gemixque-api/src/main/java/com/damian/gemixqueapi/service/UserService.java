package com.damian.gemixqueapi.service;

import com.damian.gemixqueapi.projection.user.GetFollowersInterfaceProjection;
import com.damian.gemixqueapi.projection.user.GetFollowingInterfaceProjection;
import com.damian.gemixqueapi.projection.user.UserInterfaceProjection;
import com.damian.gemixqueapi.repository.FollowersRepository;
import com.damian.gemixqueapi.repository.FollowingRepository;
import com.damian.gemixqueapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowersRepository followersRepository;

    @Autowired
    private FollowingRepository followingRepository;

    public List<UserInterfaceProjection> getAllUsers(){
        return userRepository.findAllProjectedBy();
    }

    public Optional<UserInterfaceProjection> getUserById(String id){
        return userRepository.findByUuid(id);
    }

    public Optional<GetFollowersInterfaceProjection> getFollowersOfUser(String id){
        return followersRepository.findByUuid(id);
    }

    public Optional<GetFollowingInterfaceProjection> getFollowingOfUser(String id){
        return followingRepository.findByUuid(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userDetailsObj =  userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " not found."));

        List<GrantedAuthority> authorities = new ArrayList<>(); //no authorities
        return new User(userDetailsObj.getUsername(), userDetailsObj.getPassword(), authorities);
    }
}
