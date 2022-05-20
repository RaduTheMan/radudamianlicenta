package com.org.example;


import com.api.igdb.apicalypse.APICalypse;
import com.api.igdb.apicalypse.Sort;
import com.api.igdb.exceptions.RequestException;
import com.api.igdb.request.IGDBWrapper;
import com.api.igdb.request.ProtoRequestKt;
import com.api.igdb.request.TwitchAuthenticator;
import com.api.igdb.utils.TwitchToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import proto.*;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class GameProvider {

    private final String fileNameConfig;
    private GameCredentials credentials;
    private IGDBWrapper wrapper;

    private final List<GamePrototype> gamePrototypes = new LinkedList<>();

    GameProvider(String fileNameConfig){
        this.fileNameConfig = fileNameConfig;
        this.loadCredentials();
        this.makeAuthentication();
        var games = this.getGames();
        if(Objects.nonNull(games)){
            try {
                this.createCSVFiles(games);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<GamePrototype> getGamePrototypes() {
        return gamePrototypes;
    }

    private Map<String, Object> getBasics(Game game){
        var registry = new HashMap<String, Object>();
        var customReleaseDates = game.getReleaseDatesList().stream().map(releaseDate -> new CustomReleaseDate(releaseDate.getPlatform().getName(), releaseDate.getHuman())).collect(Collectors.toList());
        registry.put("id", UUID.randomUUID().toString());
        registry.put("title", game.getName());
        registry.put("genres", game.getGenresList().stream().map(Genre::getName).collect(Collectors.toList()));
        registry.put("first_release_year", customReleaseDates.stream().sorted().findFirst().map(CustomReleaseDate::getYear).orElse("1970"));
        return registry;
    }

    private Map<String, Object> getDetails(Game game){
        var registry = new HashMap<String, Object>();
        var customReleaseDates = game.getReleaseDatesList().stream().map(releaseDate -> new CustomReleaseDate(releaseDate.getPlatform().getName(), releaseDate.getHuman())).collect(Collectors.toList());
        registry.put("id", "0");
        registry.put("platforms", game.getPlatformsList().stream().map(Platform::getName).collect(Collectors.toList()));
        registry.put("release_dates", customReleaseDates);
        registry.put("game_modes", game.getGameModesList().stream().map(GameMode::getName).collect(Collectors.toList()));
        registry.put("involved_companies", game.getInvolvedCompaniesList().stream().map(involvedCompany -> involvedCompany.getCompany().getName()).collect(Collectors.toList()));
        return registry;
    }

    private Map<String, Object> getVisuals(Game game){
        var registry = new HashMap<String, Object>();
        registry.put("id", "0");
        registry.put("screenshots", game.getScreenshotsList().stream().map(screenshot -> screenshot.getUrl().replace("t_thumb", "t_screenshot_big")).collect(Collectors.toList()));
        registry.put("summary", game.getSummary());
        registry.put("storyline", game.getStoryline());
        registry.put("cover", game.getCover().getUrl().replace("t_thumb", "t_cover_big"));
        return registry;
    }

    private Map<String, Object> getAverage(Game game){
        var registry = new HashMap<String, Object>();
        registry.put("id", "0");
        registry.put("aggregated_rating", game.getAggregatedRating());
        return registry;
    }

    private void createCSVFiles(List<Game> games) throws IOException{
        String fileNameGames = "games.csv";
        FileWriter basicsOut = new FileWriter(fileNameGames);
        String fileNameVisuals = "visuals.csv";
        FileWriter visualsOut = new FileWriter(fileNameVisuals);
        String fileNameDetails = "details.csv";
        FileWriter detailsOut = new FileWriter(fileNameDetails);
        String fileNameAverage = "average.csv";
        FileWriter averageOut = new FileWriter(fileNameAverage);

        var firstBasics = this.getBasics(games.get(0));
        var BASICS_HEADERS = firstBasics.keySet().toArray(String[]::new);

        var firstVisuals = this.getVisuals(games.get(0));
        var VISUALS_HEADERS = firstVisuals.keySet().toArray(String[]::new);

        var firstDetails = this.getDetails(games.get(0));
        var DETAILS_HEADERS = firstDetails.keySet().toArray(String[]::new);

        var firstAverage = this.getAverage(games.get(0));
        var AVERAGE_HEADERS = firstAverage.keySet().toArray(String[]::new);

        try ( CSVPrinter basicsPrinter = new CSVPrinter(basicsOut, CSVFormat.DEFAULT.withHeader(BASICS_HEADERS));
              CSVPrinter visualsPrinter = new CSVPrinter(visualsOut, CSVFormat.DEFAULT.withHeader(VISUALS_HEADERS));
              CSVPrinter detailsPrinter = new CSVPrinter(detailsOut, CSVFormat.DEFAULT.withHeader(DETAILS_HEADERS));
              CSVPrinter averagePrinter = new CSVPrinter(averageOut, CSVFormat.DEFAULT.withHeader(AVERAGE_HEADERS))) {
            for (var game : games) {
                var basics = this.getBasics(game);
                var visuals = this.getVisuals(game);
                var details = this.getDetails(game);
                var average = this.getAverage(game);

                basicsPrinter.printRecord(basics.values());

                visuals.put("id", basics.get("id"));
                visualsPrinter.printRecord(visuals.values());

                details.put("id", basics.get("id"));
                detailsPrinter.printRecord(details.values());

                average.put("id", basics.get("id"));
                averagePrinter.printRecord(average.values());

                this.gamePrototypes.add(new GamePrototype(basics.get("id").toString(), basics.get("title").toString()));
            }
        }
    }

    private void loadCredentials(){
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = GameCredentials.class.getResourceAsStream(this.fileNameConfig);
        try {
            this.credentials = mapper.readValue(is, GameCredentials.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeAuthentication(){
        TwitchAuthenticator tAuth = TwitchAuthenticator.INSTANCE;
        TwitchToken token = tAuth.requestTwitchToken(this.credentials.getClientId(), this.credentials.getClientSecret());
        this.wrapper = IGDBWrapper.INSTANCE;
        assert token != null;
        wrapper.setCredentials(this.credentials.getClientId(), token.getAccess_token());
    }

    private List<Game> getGames(){
        String FIELDS = "name,genres.name,aggregated_rating,platforms.name,release_dates.human,release_dates.platform.name,game_modes.name," +
                "involved_companies.company.name,screenshots.url,summary,storyline,cover.url,follows";
        APICalypse apicalypse = new APICalypse().fields(FIELDS).limit(300).sort("follows", Sort.DESCENDING).where("follows != null");
        try {
            return ProtoRequestKt.games(this.wrapper, apicalypse);
        } catch (RequestException e) {
            e.printStackTrace();
            System.out.println(e.getStatusCode());
            return null;
        }
    }

}
