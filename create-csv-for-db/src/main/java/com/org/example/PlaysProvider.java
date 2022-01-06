package com.org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PlaysProvider {

    PlaysProvider(List<User> users, List<String> gamesId) throws IOException {
        FileWriter out = new FileWriter("plays.csv");
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("id", "id_games_played"))) {
            for(var user: users){
                var selectedGamesId = RandomUtil.pickNRandomUsers(gamesId, 20, ThreadLocalRandom.current());
                printer.printRecord(user.getId(), selectedGamesId.toString());
            }
        }
    }
}
