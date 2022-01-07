package com.org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class GamePrototype {
    private String appid;
    private String name;

    public GamePrototype(String id, String name) {
        this.appid = id;
        this.name = name;
    }

    public GamePrototype(){ }

    @Override
    public String toString() {
        return name;
    }

    public String getId() {
        return appid;
    }

    public String getName() {
        return name;
    }
}
