package com.org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class FollowsProvider {

    FollowsProvider(List<User> users) throws IOException{
        FileWriter out = null;
        out = new FileWriter("follows.csv");
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("id,following"))) {
            List<User> copyUsers = new LinkedList<>(users);
            for(int i = 0; i < users.size(); ++i) {
                int nrFollowers = ThreadLocalRandom.current().nextInt(0, 11);
                User currentUser = copyUsers.remove(i);
                List<User> followers = this.pickNRandomUsers(copyUsers, nrFollowers, ThreadLocalRandom.current());
                printer.printRecord(currentUser.getId(), followers.toString());
                copyUsers.add(i, currentUser);
            }
        }

    }

    private List<User> pickNRandomUsers(List<User> users, int n, Random r){
        int length = users.size();
        for(int i = length - 1; i>= length - n; --i){
            Collections.swap(users, i, r.nextInt(i+1));
        }
        return users.subList(length - n, length);
    }
}
