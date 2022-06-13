package com.damian.gemixqueapi.auth;

public interface UserDetailsWithUuidProjection extends UserDetailsProjection {
    String getUuid();
}
