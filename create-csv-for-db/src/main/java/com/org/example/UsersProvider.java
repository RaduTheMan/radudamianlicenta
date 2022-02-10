package com.org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class UsersProvider {
    private final List<User> users;
    private final static String[] HEADERS = {"id", "username", "email", "password"};

    public void generateUsersFiles() throws IOException{
        String fileNameHashed = "users_hashed.csv";
        this.createCSVFile(fileNameHashed, true);
        String fileNameOriginal = "users_original.csv";
        this.createCSVFile(fileNameOriginal, false);
    }

    UsersProvider(List<User> users) {
        this.users = users;
    }

    private void createCSVFile(String fileName, boolean hashedPassword) throws IOException {
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
