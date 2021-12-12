package com.org.example;

public class Main {

    public static void main(String[] args){
        var generator = new UsersGenerator();
        var users = generator.generate();
        DataProvider provider = new DataProvider(users);
    }
}


