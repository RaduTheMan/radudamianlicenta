package com.damian.gemixqueapi.entity;

import com.damian.gemixqueapi.entity.game.GameEntity;
import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.util.List;

@Node("User")
public class UserEntity {

    @Id @GeneratedValue(UUIDStringGenerator.class)
    @Property("id")
    private String uuid;

    @Property
    private final String username;

    @Property
    private final String email;

    @Property
    private final String password;

    @Property("average_score")
    private final Double averageScore;

    @Property("nr_reviews_made")
    private final Integer nrReviewsMade;

    @Relationship(type = "PLAYS", direction = Relationship.Direction.OUTGOING)
    private List<GameEntity> gamesPlayed;

    @Relationship(type = "MAKES", direction = Relationship.Direction.OUTGOING)
    private List<ReviewEntity> reviewsMade;

    @Relationship(type = "SENT", direction = Relationship.Direction.OUTGOING)
    private List<MessageEntity> sentMessages;

    @Relationship(type = "TO", direction = Relationship.Direction.INCOMING)
    private List<MessageEntity> receivedMessages;

    @Relationship(type = "FOLLOWS", direction = Relationship.Direction.INCOMING)
    private List<UserEntity> followers;

    @Relationship(type = "FOLLOWS", direction = Relationship.Direction.OUTGOING)
    private List<UserEntity> following;

    public UserEntity(String username, String email, String password, Double averageScore, Integer nrReviewsMade) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.averageScore = averageScore;
        this.nrReviewsMade = nrReviewsMade;
    }

    public List<UserEntity> getFollowers() {
        return followers;
    }

    public List<UserEntity> getFollowing() {
        return following;
    }

    public List<GameEntity> getGamesPlayed() {
        return gamesPlayed;
    }

    public List<ReviewEntity> getReviewsMade(){
        return reviewsMade;
    }

    public List<MessageEntity> getSentMessages() {
        return sentMessages;
    }

    public List<MessageEntity> getReceivedMessages() {
        return receivedMessages;
    }

    public String getUuid() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public Integer getNrReviewsMade() {
        return nrReviewsMade;
    }
}
