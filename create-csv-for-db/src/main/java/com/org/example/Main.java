package com.org.example;

import com.github.javafaker.*;

import java.util.ArrayList;

public class Main {

    public final static int NR_USERS = 1000;

    public static void main(String[] args){
        Faker faker = new Faker();
        var users = new ArrayList<User>();
        for(int i = 0; i < NR_USERS; ++i) {
            String username = faker.name().username();
            String email = username + "@yahoo.com";
            String password = faker.random().hex();
            User user = new User(username, email, password);
            users.add(user);
        }
        DataProvider provider = new DataProvider(users);
    }
}


