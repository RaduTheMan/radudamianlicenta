package com.damian.gemixqueapi.repository;

import com.damian.gemixqueapi.entity.ReviewEntity;
import com.damian.gemixqueapi.projection.review.FullReviewInterfaceProjection;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends Neo4jRepository<ReviewEntity, String> {
    List<FullReviewInterfaceProjection> findAllProjectedBy();
    Optional<FullReviewInterfaceProjection> findByUuid(String uuid);
}
