package com.damian.gemixqueapi.service;

import com.damian.gemixqueapi.entity.ReviewEntity;
import com.damian.gemixqueapi.entity.UserEntity;
import com.damian.gemixqueapi.entity.game.GameEntity;
import com.damian.gemixqueapi.exception.InternalServerErrorException;
import com.damian.gemixqueapi.repository.RecommendationRepository;
import com.damian.gemixqueapi.repository.UncommonReviewsRepository;
import com.damian.gemixqueapi.service.custom_classes.ReviewWithGameWrapper;
import org.neo4j.driver.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private UncommonReviewsRepository uncommonReviewsRepository;

    @Autowired
    private Neo4jClient neo4jClient;

    public List<UserEntity> getSimilarUsers(String userId) {
        return recommendationRepository.findByCustomQuery(userId).orElseThrow(InternalServerErrorException::new);
    }

    public Double getWeight(String currentUserId, String otherUserId) {
        return recommendationRepository.findByCustomQuery(currentUserId, otherUserId).orElseThrow(InternalServerErrorException::new);
    }

    public List<ReviewWithGameWrapper> getUncommonReviewsForOtherUser(String currentUserId, String otherUserId) {

        String query = "MATCH (: User {id: '%s'})-[:MAKES]->(:Review)-[:ON]->(g:Game)," +
                "(other: User {id: '%s'})-[:MAKES]->(:Review)-[:ON]->(g)\n" +
                "WITH COLLECT(g.title) AS commonGames, other\n" +
                "MATCH (other)-[:MAKES]->(rev:Review)-[:ON]->(otherGame:Game)\n" +
                "WHERE NOT(otherGame.title IN commonGames)\n" +
                "RETURN rev, otherGame";
        String queryWithParameters = String.format(query, currentUserId, otherUserId);
        return new ArrayList<>(neo4jClient.query(queryWithParameters).fetchAs(ReviewWithGameWrapper.class).mappedBy((type, record) -> {
            var reviewNode = record.get("rev").asNode();
            var gameNode = record.get("otherGame").asNode();

            var reviewEntity = new ReviewEntity(reviewNode.get("content").asString(), reviewNode.get("score").asString(), reviewNode.get("time").asString());
            reviewEntity.setUuid(reviewNode.get("id").asString());

            var gameEntity = new GameEntity(gameNode.get("title").asString(), gameNode.get("genres").asList(Value::asString), gameNode.get("first_release_year").asString());
            gameEntity.setUuid(gameNode.get("id").asString());
            return new ReviewWithGameWrapper(reviewEntity, gameEntity);
        }).all());
    }

    //with each 'common' user, I should first retrieve the common games/reviews with the user that I want to recommend games for
    //after that,for each 'common' user I will get the uncommon games and I will update the scores with the formula
    //at the end, I will have a list of games with a score and I will retreive the ones with the highest score
}
