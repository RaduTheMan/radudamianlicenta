package com.damian.gemixqueapi.repository;

import com.damian.gemixqueapi.entity.ReviewEntity;
import com.damian.gemixqueapi.entity.UserEntity;
import com.damian.gemixqueapi.projection.review.ReviewInterfaceProjection;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;

public interface UncommonReviewsRepository extends Neo4jRepository<UserEntity, String> {

    //uncommon reviews between two users for the other user
    @Query("MATCH (: User {id: $currentUserId})-[:MAKES]->(:Review)-[:ON]->(g:Game)," +
            "(other: User {id: $otherUserId})-[:MAKES]->(:Review)-[:ON]->(g)\n" +
            "WITH COLLECT(g.title) AS commonGames, other\n" +
            "MATCH (other)-[:MAKES]->(rev:Review)-[:ON]->(otherGame:Game)\n" +
            "WHERE NOT(otherGame.title IN commonGames)\n" +
            "RETURN COLLECT(rev)")
    Optional<List<ReviewInterfaceProjection>> findByCustomQuery(String currentUserId, String otherUserId);
}
