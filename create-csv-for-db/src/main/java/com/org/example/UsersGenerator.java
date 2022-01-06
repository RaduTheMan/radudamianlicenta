package com.org.example;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class UsersGenerator {

    public final static int NR_USERS = 500;
    private Faker faker;

    UsersGenerator(){
        this.faker = new Faker();
    }

    public List<User> generate() {
        var users = new ArrayList<User>();
        for(int i = 0; i < NR_USERS; ++i) {
            String username = faker.name().username();
            String email = username + "@yahoo.com";
            String password = faker.random().hex();
            User user = new User(username, email, password);
            users.add(user);
        }
        return users;
    }
}
