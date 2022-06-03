package com.damian.gemixqueapi.repository;

import com.damian.gemixqueapi.entity.ReviewEntity;
import com.damian.gemixqueapi.entity.UserEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;

public interface RecommendationRepository extends Neo4jRepository<UserEntity, String> {

    //users with common games reviewed with the current user
    @Query("MATCH (u: User {id: $id})-[:MAKES]->(r1:Review)-[:ON]->(g:Game)," +
            "(other: User)-[:MAKES]->(r2:Review)-[:ON]->(g)\n" +
            "WITH COUNT(g) as commonGames, other\n" +
            "WHERE commonGames >= 3\n" +
            "RETURN other\n" +
            "ORDER BY commonGames DESC")
    Optional<List<UserEntity>> findByCustomQuery(String id);

    //Pearson correlation
    @Query("MATCH (u: User {id: $currentUserId})-[:MAKES]->(r1:Review)-[:ON]->(g:Game)," +
            "(other: User {id: $otherUserId})-[:MAKES]->(r2:Review)-[:ON]->(g)\n" +
            "WITH toInteger(r1.score) - u.average_score AS t1, toInteger(r2.score) - other.average_score AS t2\n" +
            "WITH sum(t1*t2) AS numerator, sqrt(sum(t1*t1)) * sqrt(sum(t2*t2)) AS denominator\n" +
            "RETURN numerator/denominator")
    Optional<Double> findByCustomQuery(String currentUserId, String otherUserId);
}
