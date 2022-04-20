package com.damian.gemixqueapi.repository;

import com.damian.gemixqueapi.entity.UserEntity;
import com.damian.gemixqueapi.projection.user.GetFollowingInterfaceProjection;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface FollowingRepository extends Neo4jRepository<UserEntity, String> {
    Optional<GetFollowingInterfaceProjection> findByUuid(String uuid);
}
