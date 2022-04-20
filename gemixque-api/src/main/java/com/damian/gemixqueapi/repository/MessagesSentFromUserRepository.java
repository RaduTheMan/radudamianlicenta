package com.damian.gemixqueapi.repository;

import com.damian.gemixqueapi.entity.UserEntity;
import com.damian.gemixqueapi.projection.user.GetMessagesSentFromUserInterfaceProjection;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface MessagesSentFromUserRepository extends Neo4jRepository<UserEntity, String> {
    Optional<GetMessagesSentFromUserInterfaceProjection> findByUuid(String uuid);
}
