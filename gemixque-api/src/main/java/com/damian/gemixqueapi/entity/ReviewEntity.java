package com.damian.gemixqueapi.entity;

import com.damian.gemixqueapi.entity.game.GameEntity;
import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import javax.management.relation.Relation;
import java.util.List;

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

    @Relationship(type = "MAKES", direction = Relationship.Direction.INCOMING)
    private UserEntity userEntity;

    @Relationship(type = "ON", direction = Relationship.Direction.OUTGOING)
    private GameEntity gameEntity;

    @Relationship(type = "ON", direction = Relationship.Direction.INCOMING)
    private List<CommentEntity> comments;

    public ReviewEntity(String content, String score, String time) {
        this.content = content;
        this.score = score;
        this.time = time;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public GameEntity getGameEntity() {
        return gameEntity;
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
