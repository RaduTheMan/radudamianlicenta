package com.org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private String username;
    private String email;
    private String originalPassword;
    private String hashedPassword;
    private String id;
    private List<GamePrototype> playedGames;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id.equals(user.id);
    }

    @Override
    public String toString() {
        return id;
    }

    public User(String username, String email, String original_password){
        this.username = username;
        this.email = email;
        this.originalPassword = original_password;
        this.hashedPassword = PasswordUtil.generateHashedPassword(this.originalPassword);
        this.id = UUID.randomUUID().toString();
    }

    public List<GamePrototype> getPlayedGames() {
        return playedGames;
    }

    public void setPlayedGames(List<GamePrototype> playedGames) {
        this.playedGames = playedGames;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOriginalPassword() {
        return this.originalPassword;
    }

    public String getHashedPassword() {
        return this.hashedPassword;
    }

    public void setOriginalPasswordPassword(String originalPassword) {
        this.originalPassword = originalPassword;
        this.hashedPassword = PasswordUtil.generateHashedPassword(this.originalPassword);
    }
}
