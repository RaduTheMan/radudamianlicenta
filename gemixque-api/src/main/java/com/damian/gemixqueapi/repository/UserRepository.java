package com.damian.gemixqueapi.repository;

import com.damian.gemixqueapi.entity.UserEntity;
import com.damian.gemixqueapi.projection.UserInterfaceProjection;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends Neo4jRepository<UserEntity, String> {
    List<UserInterfaceProjection> findAllProjectedBy();
    Optional<UserInterfaceProjection> findByUuid(String uuid);
}
