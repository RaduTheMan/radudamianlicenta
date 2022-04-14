package com.damian.gemixqueapi.entity;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
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

    public GameEntity(String title, List<String> genres, String firstReleaseYear) {
        this.title = title;
        this.genres = genres;
        this.firstReleaseYear = firstReleaseYear;
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
