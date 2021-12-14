package com.org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class UsersProvider {
    private List<User> users;
    private String fileNameOriginal = "users_original.csv";
    private String fileNameHashed = "users_hashed.csv";
    private final static String[] HEADERS = {"id", "username", "email", "password"};

    UsersProvider(List<User> users) {
        this.users = users;
        try {
            this.createCSVFile(fileNameHashed, true);
            this.createCSVFile(fileNameOriginal, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createCSVFile(String fileName, boolean hashedPassword) throws IOException {
        FileWriter out = new FileWriter(fileName);
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(HEADERS))) {
            for(var user: users){
                if(hashedPassword) {
                    printer.printRecord(user.getId(), user.getUsername(), user.getEmail(), user.getHashedPassword());
                }
                else{
                    printer.printRecord(user.getId(), user.getUsername(), user.getEmail(), user.getOriginalPassword());
                }
            }
        }
    }
}
