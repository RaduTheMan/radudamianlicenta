package com.org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class FollowsProvider {

    public static void generateFollowsFile(List<User> users) throws IOException {
        FileWriter out = new FileWriter("follows.csv");
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("id", "following"))) {
            List<User> copyUsers = new LinkedList<>(users);
            for(int i = 0; i < users.size(); ++i) {
                int nrFollowers = ThreadLocalRandom.current().nextInt(0, 11);
                User currentUser = copyUsers.remove(i);
                List<User> followers = RandomUtil.pickNRandomUsers(copyUsers, nrFollowers, ThreadLocalRandom.current());
                printer.printRecord(currentUser.getId(), followers.toString());
                copyUsers.add(i, currentUser);
            }
        }
    }

}
