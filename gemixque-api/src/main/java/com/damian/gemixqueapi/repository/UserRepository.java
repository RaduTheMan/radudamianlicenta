package com.damian.gemixqueapi.repository;

import com.damian.gemixqueapi.entity.UserEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;


public interface UserRepository extends Neo4jRepository<UserEntity, String> {
}
