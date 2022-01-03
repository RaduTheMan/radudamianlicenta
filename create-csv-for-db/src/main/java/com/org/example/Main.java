package com.org.example;

public class Main {

    public static void main(String[] args){
//        var generator = new UsersGenerator();
//        var users = generator.generate();
//        UsersProvider usersProvider = new UsersProvider(users);
        GameProvider gameProvider = new GameProvider("/igdb-api-config.json");
    }
}


