package com.org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ReviewProvider {

    private List<SteamGame> steamGames;
    private final Map<SteamGame, JsonNode> reviewsRegistry = new HashMap<>();
    private final Set<SteamGame> visited = new HashSet<>();
    private final List<Review> allReviews = new ArrayList<>();

    ReviewProvider(List<User> users) throws IOException {
        this.readSteamGames();
        FileWriter out = new FileWriter("reviews.csv");
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("id_review","id_user", "score", "content", "time", "id_game"))) {
            Faker faker = new Faker();
            var randomReviewsIterator = this.getIteratorFromRandomReviewsFile();
            for (var user : users) {
                var playedGames = user.getPlayedGames();
                var reviewedGames = RandomUtil.pickNRandomUsers(playedGames, 5, ThreadLocalRandom.current());
                for (var reviewedGame : reviewedGames) {
                    var maybeSteamGame = steamGames.stream().filter(steamGame -> steamGame.getName().startsWith(reviewedGame.getName())).sorted().findFirst();
                    String review = "";
                    int score = ThreadLocalRandom.current().nextInt(1, 11);
                    String time = faker.date().past(faker.random().nextInt(1, 1000), TimeUnit.DAYS).toString();
                    boolean takeRandom = true;
                    if(maybeSteamGame.isPresent()){
                        var steamGame = maybeSteamGame.get();
                        this.updateReviewsRegistry(steamGame);
                        if(reviewsRegistry.containsKey(steamGame)){
                            var response = takeSteamReview(steamGame);
                            review = response.getLeft();
                            boolean votedUp = response.getRight();
                            if(votedUp)
                                score = ThreadLocalRandom.current().nextInt(7, 11);
                            else
                                score = ThreadLocalRandom.current().nextInt(1, 7);
                            review = this.cleanReview(review);
                            takeRandom = false;
                        }
                    }
                    if(takeRandom){
                        var record = randomReviewsIterator.next();
                        review = record.get("user_review");
                    }
                    String idReview = UUID.randomUUID().toString();
                    printer.printRecord(idReview, user.getId(), score, review, time, reviewedGame.getId());
                    allReviews.add(new Review(idReview, score, review, time));
                }
            }
        }
    }

    public List<Review> getAllReviews() {
        return allReviews;
    }

    private String cleanReview(String review) {
        if(review.length() > 2) {
            review = review.substring(1, review.length() - 2);
        }
        review = review.replace("\\", "");
        return review;
    }

    private void readSteamGames() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String STEAM_GAMES_FILE_NAME = "steam-games.json";
        this.steamGames = Arrays.asList(mapper.readValue(Paths.get(STEAM_GAMES_FILE_NAME).toFile(), SteamGame[].class));
    }

    private void updateReviewsRegistry(SteamGame steamGame) {
        if(visited.contains(steamGame)){
            return;
        }
        visited.add(steamGame);
        if(!reviewsRegistry.containsKey(steamGame)){
            var reviewsWrapper = this.getSteamReviews(steamGame.getAppid());
            if(Objects.nonNull(reviewsWrapper) && !reviewsWrapper.isEmpty()){
                reviewsRegistry.put(steamGame, reviewsWrapper);
            }
        }
    }

    private Pair<String, Boolean> takeSteamReview(SteamGame steamGame){
        var reviewsWrapper = reviewsRegistry.get(steamGame);
        var votes = reviewsWrapper.findValues("voted_up");
        var reviews = reviewsWrapper.findValues("review");
        int index = ThreadLocalRandom.current().nextInt(0, reviews.size());
        var review = reviews.get(index);
        var vote = votes.get(index);
        return new ImmutablePair<>(review.toString(), vote.asBoolean());
    }

    private Iterator<CSVRecord> getIteratorFromRandomReviewsFile() throws IOException {
        FileInputStream fileNameInput = new FileInputStream("random_reviews.csv");
        InputStreamReader input = new InputStreamReader(fileNameInput);
        CSVParser csvParser = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(input);
        return csvParser.iterator();
    }

    private JsonNode getSteamReviews(int appid){
        String url = String.format("https://store.steampowered.com/appreviews/%1$s?json=1&num_per_page=100&language=english", appid);
        try {
            var response = MakeRequestUtil.makeGETRequest(url);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(response);
            return jsonNode.get("reviews");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
