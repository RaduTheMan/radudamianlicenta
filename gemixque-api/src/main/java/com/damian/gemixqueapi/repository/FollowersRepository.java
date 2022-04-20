package com.damian.gemixqueapi.repository;

import com.damian.gemixqueapi.entity.UserEntity;
import com.damian.gemixqueapi.projection.user.GetFollowersInterfaceProjection;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface FollowersRepository extends Neo4jRepository<UserEntity, String> {
    Optional<GetFollowersInterfaceProjection> findByUuid(String uuid);
}
