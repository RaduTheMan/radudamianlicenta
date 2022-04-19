package com.damian.gemixqueapi.entity;

import org.springframework.data.neo4j.core.schema.*;

@Node("Comment")
public class CommentEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Property
    private final String content;

    @Property
    private final String time;

    @Relationship(type = "MAKES", direction = Relationship.Direction.INCOMING)
    private UserEntity userEntity;

    @Relationship(type = "ON", direction = Relationship.Direction.OUTGOING)
    private ReviewEntity reviewEntity;

    public CommentEntity(String content, String time) {
        this.content = content;
        this.time = time;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public ReviewEntity getReviewEntity() {
        return reviewEntity;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }
}
