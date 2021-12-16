package com.org.example;


import com.api.igdb.apicalypse.APICalypse;
import com.api.igdb.apicalypse.Sort;
import com.api.igdb.exceptions.RequestException;
import com.api.igdb.request.IGDBWrapper;
import com.api.igdb.request.ProtoRequestKt;
import com.api.igdb.request.TwitchAuthenticator;
import com.api.igdb.utils.TwitchToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import proto.Game;
import proto.Genre;
import proto.Search;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

public class GameProvider {

    private final String fileNameConfig;
    private GameCredentials credentials;
    private TwitchAuthenticator tAuth;
    private TwitchToken token;
    private IGDBWrapper wrapper;
    private final String FIELDS = "name,genres.name,aggregated_rating,platforms.name,release_dates.human,game_modes.name," +
            "involved_companies.company.name,screenshots.url,summary,storyline,cover.url,follows";


    GameProvider(String fileNameConfig){
        this.fileNameConfig = fileNameConfig;
        this.loadCredentials();
        this.makeAuthentication();
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
        this.tAuth = TwitchAuthenticator.INSTANCE;
        this.token = this.tAuth.requestTwitchToken(this.credentials.getClientId(), this.credentials.getClientSecret());
        this.wrapper = IGDBWrapper.INSTANCE;
        wrapper.setCredentials(this.credentials.getClientId(), this.token.getAccess_token());
    }

    public void getGames(){
        APICalypse apicalypse = new APICalypse().fields(this.FIELDS).limit(300).sort("follows", Sort.DESCENDING).where("follows != null");
        try {
            List<Game> games = ProtoRequestKt.games(this.wrapper, apicalypse);
            System.out.println(games);
//            for(var game: games){
//                var genres = this.getGenresOfGame(game);
//            }
        } catch (RequestException e) {
            e.printStackTrace();
            System.out.println(e.getStatusCode());
        }
    }

}
