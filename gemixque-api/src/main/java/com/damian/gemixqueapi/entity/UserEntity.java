package com.damian.gemixqueapi.entity;

import com.damian.gemixqueapi.entity.game.GameEntity;
import com.damian.gemixqueapi.projection.GameInterfaceProjection;
import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.util.List;

@Node("User")
public class UserEntity {

    @Id @GeneratedValue(UUIDStringGenerator.class)
    @Property("id")
    private String uuid;

    @Property
    private final String username;

    @Property
    private final String email;

    @Property
    private final String password;

    @Relationship(type = "PLAYS", direction = Relationship.Direction.OUTGOING)
    private List<GameEntity> gamesPlayed;

    public UserEntity(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public List<GameEntity> getGamesPlayed() {
        return gamesPlayed;
    }

    public String getUuid() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
