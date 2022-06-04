package com.damian.gemixqueapi.entity.game;


import com.damian.gemixqueapi.entity.ReviewEntity;
import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.util.List;
import java.util.Objects;

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

    @Relationship(type = "ON", direction = Relationship.Direction.INCOMING)
    private List<ReviewEntity> reviewsMade;


    public GameEntity(String title, List<String> genres, String firstReleaseYear) {
        this.title = title;
        this.genres = genres;
        this.firstReleaseYear = firstReleaseYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameEntity that = (GameEntity) o;

        return Objects.equals(uuid, that.uuid);
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

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
