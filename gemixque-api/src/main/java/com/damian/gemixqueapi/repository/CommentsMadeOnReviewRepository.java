package com.damian.gemixqueapi.repository;

import com.damian.gemixqueapi.entity.ReviewEntity;
import com.damian.gemixqueapi.projection.review.GetCommentsMadeOnReviewInterfaceProjection;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface CommentsMadeOnReviewRepository extends Neo4jRepository<ReviewEntity, String> {
    Optional<GetCommentsMadeOnReviewInterfaceProjection> findByUuid(String uuid);
}
