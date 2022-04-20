package com.damian.gemixqueapi.repository;

import com.damian.gemixqueapi.entity.UserEntity;
import com.damian.gemixqueapi.projection.user.GetMessagesReceivedByUserInterfaceProjection;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface MessagesReceivedByUserRepository extends Neo4jRepository<UserEntity, String> {
    Optional<GetMessagesReceivedByUserInterfaceProjection> findByUuid(String uuid);
}
