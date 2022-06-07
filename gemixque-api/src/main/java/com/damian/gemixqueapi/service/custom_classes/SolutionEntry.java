package com.damian.gemixqueapi.service.custom_classes;

import com.damian.gemixqueapi.entity.game.GameEntity;

public class SolutionEntry {

    private String title;
    private String uuid;
    private double value;

    public SolutionEntry(GameEntity game, Fraction fraction) {
        this.title = game.getTitle();
        this.uuid = game.getUuid();
        this.value = fraction.getValue();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
