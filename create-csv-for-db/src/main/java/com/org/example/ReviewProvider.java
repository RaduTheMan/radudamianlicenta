package com.org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import jdk.swing.interop.SwingInterOpUtils;
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
    private Map<SteamGame, JsonNode> reviewsRegistry = new HashMap<>();
    private Set<SteamGame> visited = new HashSet<>();
    private List<Review> allReviews = new ArrayList<>();

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
                            review = takeSteamReview(steamGame);
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
        review = review.substring(1, review.length() - 2);
        review = review.replace("\\", "");
        return review;
    }

    private void readSteamGames() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
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

    private String takeSteamReview(SteamGame steamGame){
        var reviewsWrapper = reviewsRegistry.get(steamGame);
        var reviews = reviewsWrapper.findValues("review");
        var review = reviews.get(ThreadLocalRandom.current().nextInt(0, reviews.size()));
        return review.toString();
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
