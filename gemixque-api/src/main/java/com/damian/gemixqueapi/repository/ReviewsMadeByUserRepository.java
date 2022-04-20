package com.damian.gemixqueapi.repository;

import com.damian.gemixqueapi.entity.UserEntity;
import com.damian.gemixqueapi.projection.user.GetReviewsMadeFromUserInterfaceProjection;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface ReviewsMadeByUserRepository extends Neo4jRepository<UserEntity, String> {
    Optional<GetReviewsMadeFromUserInterfaceProjection> findByUuid(String uuid);
}
