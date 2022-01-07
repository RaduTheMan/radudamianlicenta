package com.org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StoreGamesUtil {

    private static final String GET_URL = "https://api.steampowered.com/ISteamApps/GetAppList/v2";

    public static void storeSteamGames() throws IOException {

            var response = MakeRequestUtil.makeGETRequest(GET_URL);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(response);
            var steamGamesList = jsonNode.get("applist").get("apps");

            Path path = Paths.get("steam-games.json");
            Files.write(path, steamGamesList.toString().getBytes());
    }
}
