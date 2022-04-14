package com.damian.gemixqueapi.entity.game;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.List;

@Node("Visuals")
public class VisualsEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Property
    private final List<String> screenshots;

    @Property
    private final String cover;

    @Property
    private final String storyline;

    @Property
    private final String summary;

    public VisualsEntity(List<String> screenshots, String cover, String storyline, String summary) {
        this.screenshots = screenshots;
        this.cover = cover;
        this.storyline = storyline;
        this.summary = summary;
    }

    public Long getId() {
        return id;
    }

    public List<String> getScreenshots() {
        return screenshots;
    }

    public String getCover() {
        return cover;
    }

    public String getStoryline() {
        return storyline;
    }

    public String getSummary() {
        return summary;
    }
}
