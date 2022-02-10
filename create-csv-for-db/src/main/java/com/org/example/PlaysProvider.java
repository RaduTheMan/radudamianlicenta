package com.org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class PlaysProvider {

    public static void generatePlaysFile(List<User> users, List<GamePrototype> gamePrototypes) throws IOException{
        FileWriter out = new FileWriter("plays.csv");
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("id", "id_games_played"))) {
            for(var user: users) {
                var selectedGamePrototypes = RandomUtil.pickNRandomUsers(gamePrototypes, 20, ThreadLocalRandom.current());
                printer.printRecord(user.getId(), selectedGamePrototypes.stream().map(GamePrototype::getId).collect(Collectors.toList()).toString());
                user.setPlayedGames(new ArrayList<>(selectedGamePrototypes));
            }
        }
    }

}
