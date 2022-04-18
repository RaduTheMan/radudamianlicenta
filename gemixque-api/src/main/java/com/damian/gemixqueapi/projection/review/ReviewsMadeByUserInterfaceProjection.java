package com.damian.gemixqueapi.projection.review;

import com.damian.gemixqueapi.projection.game.GameIdInterfaceProjection;

public interface ReviewsMadeByUserInterfaceProjection extends ReviewInterfaceProjection {
    GameIdInterfaceProjection getGameEntity();
}
