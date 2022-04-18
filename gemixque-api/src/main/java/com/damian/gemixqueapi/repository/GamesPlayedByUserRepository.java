package com.damian.gemixqueapi.repository;

import com.damian.gemixqueapi.entity.UserEntity;
import com.damian.gemixqueapi.projection.user.GamesPlayedInterfaceProjection;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface GamesPlayedByUserRepository extends Neo4jRepository<UserEntity, String> {
    Optional<GamesPlayedInterfaceProjection> findByUuid(String uuid);
}
