package com.damian.gemixqueapi.repository;

import com.damian.gemixqueapi.auth.UserDetailsProjection;
import com.damian.gemixqueapi.auth.UserDetailsWithUuidProjection;
import com.damian.gemixqueapi.entity.UserEntity;
import com.damian.gemixqueapi.projection.user.UserInterfaceProjection;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends Neo4jRepository<UserEntity, String> {
    List<UserInterfaceProjection> findAllProjectedBy();
    Optional<UserInterfaceProjection> findByUuid(String uuid);
    Optional<UserDetailsWithUuidProjection> findByUsername(String username);
}
