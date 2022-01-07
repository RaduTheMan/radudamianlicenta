package com.org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class ReviewProvider {

    private final String STEAM_GAMES_FILE_NAME = "steam-games.json";
    private List<SteamGame> steamGames;

    ReviewProvider(List<User> users) throws IOException {
        this.readSteamGames();
        for(var user: users){
            var playedGames = user.getPlayedGames();
            var reviewedGames = RandomUtil.pickNRandomUsers(playedGames, playedGames.size(), ThreadLocalRandom.current());
            for(var reviewedGame: reviewedGames){
                var maybeSteamGame = steamGames.stream().filter(steamGame -> steamGame.getName().equals(reviewedGame.getName())).findFirst();
                String review = null;
                if(maybeSteamGame.isPresent()) {
                    review = this.getSteamReview(maybeSteamGame.get().getAppid());
                }
                if(Objects.isNull(review)){
                    //TAKE A RANDOM REVIEW
                }
            }
        }
    }

    private void readSteamGames() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        this.steamGames = Arrays.asList(mapper.readValue(Paths.get(STEAM_GAMES_FILE_NAME).toFile(), SteamGame[].class));
    }

    private String getSteamReview(int appid){
        String url = String.format("https://store.steampowered.com/appreviews/%1$s?json=1&num_per_page=100&language=english", appid);
        try {
            var response = MakeRequestUtil.makeGETRequest(url);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(response);
            var reviews = jsonNode.get("reviews");
            var reviewWrapper = reviews.get(ThreadLocalRandom.current().nextInt(0, 100));
            if(Objects.nonNull(reviewWrapper)){
                var review = reviewWrapper.get("review");
                return review;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
