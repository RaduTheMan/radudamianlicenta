package com.damian.gemixqueapi.service;

import com.damian.gemixqueapi.accessor.RecordMapAccessor;
import com.damian.gemixqueapi.entity.ReviewEntity;
import com.damian.gemixqueapi.entity.UserEntity;
import com.damian.gemixqueapi.exception.InternalServerErrorException;
import com.damian.gemixqueapi.repository.RecommendationRepository;
import com.damian.gemixqueapi.repository.UncommonReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.data.neo4j.core.mapping.Neo4jMappingContext;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class RecommendationService {

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private UncommonReviewsRepository uncommonReviewsRepository;

    @Autowired
    private Neo4jClient neo4jClient;

    @Autowired
    private Neo4jMappingContext neo4jMappingContext;

    public List<UserEntity> getSimilarUsers(String userId) {
        return recommendationRepository.findByCustomQuery(userId).orElseThrow(InternalServerErrorException::new);
    }

    public Double getWeight(String currentUserId, String otherUserId) {
        return recommendationRepository.findByCustomQuery(currentUserId, otherUserId).orElseThrow(InternalServerErrorException::new);
    }

    public Collection<ReviewEntity> getUncommonReviewsForOtherUser(String currentUserId, String otherUserId) {

        String query = "MATCH (: User {id: '%s'})-[:MAKES]->(:Review)-[:ON]->(g:Game)," +
                "(other: User {id: '%s'})-[:MAKES]->(:Review)-[:ON]->(g)\n" +
                "WITH COLLECT(g.title) AS commonGames, other\n" +
                "MATCH (other)-[:MAKES]->(rev:Review)-[:ON]->(otherGame:Game)\n" +
                "WHERE NOT(otherGame.title IN commonGames)\n" +
                "RETURN rev";
        String queryWithParameters = String.format(query, currentUserId, otherUserId);
        var mappingFunction = neo4jMappingContext.getRequiredMappingFunctionFor(ReviewEntity.class);
        return neo4jClient.query(queryWithParameters).fetchAs(ReviewEntity.class).mappedBy((type, reviewObject) -> {
            return mappingFunction.apply(type, new RecordMapAccessor(reviewObject));
        }).all();
    }

    //with each 'common' user, I should first retrieve the common games/reviews with the user that I want to recommend games for
    //after that,for each 'common' user I will get the uncommon games and I will update the scores with the formula
    //at the end, I will have a list of games with a score and I will retreive the ones with the highest score
}
