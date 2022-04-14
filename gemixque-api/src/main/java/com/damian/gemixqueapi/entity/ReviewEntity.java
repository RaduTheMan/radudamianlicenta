package com.damian.gemixqueapi.entity;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Node("Review")
public class ReviewEntity {

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    @Property("id")
    private String uuid;

    @Property
    private final String content;

    @Property
    private final String score;

    @Property
    private final String time;

    public ReviewEntity(String content, String score, String time) {
        this.content = content;
        this.score = score;
        this.time = time;
    }

    public String getUuid() {
        return uuid;
    }

    public String getContent() {
        return content;
    }

    public String getScore() {
        return score;
    }

    public String getTime() {
        return time;
    }
}
