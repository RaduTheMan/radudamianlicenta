package com.damian.gemixqueapi.repository;

import com.damian.gemixqueapi.entity.game.GameEntity;
import com.damian.gemixqueapi.projection.game.GetReviewsMadeOnGameInterfaceProjection;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface ReviewsMadeOnGameRepository extends Neo4jRepository<GameEntity, String> {
    Optional<GetReviewsMadeOnGameInterfaceProjection> findByUuid(String uuid);
}
