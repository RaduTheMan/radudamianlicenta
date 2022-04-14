package com.damian.gemixqueapi.entity.game;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.List;
import java.util.stream.Collectors;

@Node("Details")
public class DetailsEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Property("game_modes")
    private final List<String> gameModes;

    @Property("involved_companies")
    private final List<String> involvedCompanies;

    @Property
    private final List<String> platforms;

    @Property("release_dates")
    private final List<String> releaseDates;

    public DetailsEntity(List<String> gameModes, List<String> involvedCompanies, List<String> platforms, List<String> releaseDates) {
        this.gameModes = gameModes;
        this.involvedCompanies = involvedCompanies;
        this.platforms = platforms;
        this.releaseDates = releaseDates;
    }

    public Long getId() {
        return id;
    }

    public List<String> getGameModes() {
        return gameModes;
    }

    public List<String> getInvolvedCompanies() {
        return involvedCompanies;
    }

    public List<String> getPlatforms() {
        return platforms;
    }

    public List<CustomReleaseDate> getReleaseDates() {
        var gson = new Gson();
        return releaseDates.stream().map(releaseDate -> {
            if(releaseDate.endsWith("*")){
                releaseDate = releaseDate.replace("*", "");
            }
            return gson.fromJson(releaseDate, CustomReleaseDate.class);
        }).collect(Collectors.toList());
    }
}
