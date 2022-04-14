package com.damian.gemixqueapi.repository;

import com.damian.gemixqueapi.entity.MessageEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface MessageRepository extends Neo4jRepository<MessageEntity, Long> {
}
