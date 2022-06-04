package com.damian.gemixqueapi.service.custom_classes;

import com.damian.gemixqueapi.entity.ReviewEntity;
import com.damian.gemixqueapi.entity.game.GameEntity;

public class ReviewWithGameWrapper {
    private ReviewEntity rev;
    private GameEntity otherGame;

    public ReviewWithGameWrapper(ReviewEntity reviewEntity, GameEntity gameEntity) {
        this.rev = reviewEntity;
        this.otherGame = gameEntity;
    }

    public ReviewEntity getRev() {
        return rev;
    }

    public GameEntity getOtherGame() {
        return otherGame;
    }

    public void setRev(ReviewEntity rev) {
        this.rev = rev;
    }

    public void setOtherGame(GameEntity otherGame) {
        this.otherGame = otherGame;
    }
}
