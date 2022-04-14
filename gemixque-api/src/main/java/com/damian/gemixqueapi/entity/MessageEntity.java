package com.damian.gemixqueapi.entity;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node("Message")
public class MessageEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Property
    private final String content;

    @Property
    private final String time;

    public MessageEntity(String content, String time) {
        this.content = content;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }
}
