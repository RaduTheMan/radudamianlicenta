package com.damian.gemixqueapi.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserWithUuid extends User implements UserDetailsWithUuidInterface{

    private final String uuid;

    public UserWithUuid(String username, String password, String uuid, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.uuid = uuid;
    }

    @Override
    public String getUuid() {
        return uuid;
    }
}
