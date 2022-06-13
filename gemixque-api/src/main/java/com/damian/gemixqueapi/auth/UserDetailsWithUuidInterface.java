package com.damian.gemixqueapi.auth;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsWithUuidInterface extends UserDetails {
    String getUuid();
}
