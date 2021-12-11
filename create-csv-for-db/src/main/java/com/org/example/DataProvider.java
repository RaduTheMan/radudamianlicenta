package com.org.example;

import java.util.List;

public class DataProvider {
    private List<User> users;
    private String fileName = "users.csv";

    DataProvider(List<User> users) {
        this.users = users;
    }
}
