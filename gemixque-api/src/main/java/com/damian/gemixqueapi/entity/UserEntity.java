package com.damian.gemixqueapi.entity;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Node("User")
public class UserEntity {

    @Id @GeneratedValue(UUIDStringGenerator.class)
    private final String id;

    private final String username;

    private final String email;

    private final String password;

    public UserEntity(String username, String email, String password) {
        this.id = null;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
