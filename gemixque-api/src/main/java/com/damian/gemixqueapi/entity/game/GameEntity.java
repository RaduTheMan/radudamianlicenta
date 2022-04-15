package com.damian.gemixqueapi.entity.game;


import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.util.List;

@Node("Game")
public class GameEntity {

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    @Property("id")
    private String uuid;

    @Property
    private final String title;

    @Property
    private final List<String> genres;

    @Property("first_release_year")
    private final String firstReleaseYear;

    @Relationship(type = "HAS", direction = Relationship.Direction.OUTGOING)
    private VisualsEntity visuals;

    @Relationship(type = "HAS", direction = Relationship.Direction.OUTGOING)
    private DetailsEntity details;

    @Relationship(type = "HAS", direction = Relationship.Direction.OUTGOING)
    private AverageEntity average;


    public GameEntity(String title, List<String> genres, String firstReleaseYear) {
        this.title = title;
        this.genres = genres;
        this.firstReleaseYear = firstReleaseYear;
    }

    public VisualsEntity getVisuals() {
        return visuals;
    }

    public DetailsEntity getDetails() {
        return details;
    }

    public AverageEntity getAverage() {
        return average;
    }

    public String getUuid() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getFirstReleaseYear() {
        return firstReleaseYear;
    }
}
