package com.damian.gemixqueapi.repository;

import com.damian.gemixqueapi.entity.ReviewEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ReviewRepository extends Neo4jRepository<ReviewEntity, String> {
}
