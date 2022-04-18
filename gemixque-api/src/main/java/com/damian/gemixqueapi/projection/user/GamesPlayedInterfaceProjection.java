package com.damian.gemixqueapi.projection.user;

import com.damian.gemixqueapi.projection.game.GameInterfaceProjection;

import java.util.List;

public interface GamesPlayedInterfaceProjection {
    List<GameInterfaceProjection> getGamesPlayed();
}
