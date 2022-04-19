package com.damian.gemixqueapi.entity;

import org.springframework.data.neo4j.core.schema.*;

@Node("Message")
public class MessageEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Property
    private final String content;

    @Property
    private final String time;

    @Relationship(type = "SENT", direction = Relationship.Direction.INCOMING)
    private UserEntity sender;

    @Relationship(type = "TO", direction = Relationship.Direction.OUTGOING)
    private UserEntity receiver;

    public MessageEntity(String content, String time) {
        this.content = content;
        this.time = time;
    }

    public UserEntity getSender() {
        return sender;
    }

    public UserEntity getReceiver() {
        return receiver;
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
