package com.org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ReviewProvider {

    private final String STEAM_GAMES_FILE_NAME = "steam-games.json";
    private List<SteamGame> steamGames;

    ReviewProvider(List<User> users) throws IOException {
        this.readSteamGames();
        FileWriter out = new FileWriter("reviews.csv");
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("id_user", "score", "content", "time", "id_game"))) {
            Faker faker = new Faker();
            var randomReviewsIterator = this.getIteratorFromRandomReviewsFile();
            for (var user : users) {
                var playedGames = user.getPlayedGames();
                var reviewedGames = RandomUtil.pickNRandomUsers(playedGames, 5, ThreadLocalRandom.current());
                for (var reviewedGame : reviewedGames) {
                    var maybeSteamGame = steamGames.stream().filter(steamGame -> steamGame.getName().startsWith(reviewedGame.getName())).findFirst();
                    String review = null;
                    int score = 0;
                    String time = faker.date().past(faker.random().nextInt(1, 1000), TimeUnit.DAYS).toString();
                    if (maybeSteamGame.isPresent()) {
                        var response = this.getSteamReview(maybeSteamGame.get().getAppid());
                        if(Objects.nonNull(response)) {
                            review = response.get("review");
                            if (response.get("votedUp").equals("true")) {
                                score = ThreadLocalRandom.current().nextInt(6, 11);
                            } else {
                                score = ThreadLocalRandom.current().nextInt(0, 6);
                            }
                        }
                        else{
                            review = randomReviewsIterator.next().get("user_review");
                            score = ThreadLocalRandom.current().nextInt(0, 11);
                        }
                    }
                    else{
                        review = randomReviewsIterator.next().get("user_review");
                        score = ThreadLocalRandom.current().nextInt(0, 11);
                    }
                    printer.printRecord(user.getId(), score, review, time, reviewedGame.getId());

                }
            }
        }
    }

    private void readSteamGames() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        this.steamGames = Arrays.asList(mapper.readValue(Paths.get(STEAM_GAMES_FILE_NAME).toFile(), SteamGame[].class));
    }

    private Iterator<CSVRecord> getIteratorFromRandomReviewsFile() throws IOException {
        FileInputStream fileNameInput = new FileInputStream("random_reviews.csv");
        InputStreamReader input = new InputStreamReader(fileNameInput);
        CSVParser csvParser = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(input);
        return csvParser.iterator();
    }

    private Map<String, String> getSteamReview(int appid){
        String url = String.format("https://store.steampowered.com/appreviews/%1$s?json=1&num_per_page=100&language=english", appid);
        try {
            var response = MakeRequestUtil.makeGETRequest(url);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(response);
            var reviews = jsonNode.get("reviews");
            var reviewWrapper = reviews.get(ThreadLocalRandom.current().nextInt(0, 100));
            if(Objects.nonNull(reviewWrapper)){
                var review = reviewWrapper.get("review");
                var votedUp = reviewWrapper.get("voted_up");
                var map = new HashMap<String, String>();
                map.put("review", review.asText());
                map.put("votedUp", votedUp.asText());
                return map;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
