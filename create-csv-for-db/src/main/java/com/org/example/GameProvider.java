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
import proto.Search;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class GameProvider {

    private final String fileNameConfig;
    private GameCredentials credentials;
    private TwitchAuthenticator tAuth;
    private TwitchToken token;
    private IGDBWrapper wrapper;


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
        APICalypse apicalypse = new APICalypse().fields("name,summary");
        try {
            List<Game> games = ProtoRequestKt.games(this.wrapper, apicalypse);
            System.out.println(games);
        } catch (RequestException e) {
            e.printStackTrace();
            System.out.println(e.getStatusCode());
        }
    }

}
