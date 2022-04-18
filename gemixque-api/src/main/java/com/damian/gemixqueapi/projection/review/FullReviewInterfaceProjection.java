package com.damian.gemixqueapi.projection.review;

import com.damian.gemixqueapi.projection.game.GameIdInterfaceProjection;
import com.damian.gemixqueapi.projection.user.UserIdInterfaceProjection;

public interface FullReviewInterfaceProjection extends ReviewInterfaceProjection{
    GameIdInterfaceProjection getGameEntity();
    UserIdInterfaceProjection getUserEntity();
}
