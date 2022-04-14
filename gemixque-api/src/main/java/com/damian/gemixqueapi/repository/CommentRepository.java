package com.damian.gemixqueapi.repository;

import com.damian.gemixqueapi.entity.CommentEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CommentRepository extends Neo4jRepository<CommentEntity, Long> {
}
