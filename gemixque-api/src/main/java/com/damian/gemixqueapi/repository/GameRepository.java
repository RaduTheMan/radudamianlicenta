package com.damian.gemixqueapi.repository;

import com.damian.gemixqueapi.entity.game.GameEntity;
import com.damian.gemixqueapi.projection.GameInterfaceProjection;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends Neo4jRepository<GameEntity, String> {
    List<GameInterfaceProjection> findAllProjectedBy();
    Optional<GameInterfaceProjection> findByUuid(String uuid);
}
