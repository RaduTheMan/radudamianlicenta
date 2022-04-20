package com.damian.gemixqueapi.projection.user;

import java.util.List;

public interface GetFollowingInterfaceProjection {
    List<UserIdInterfaceProjection> getFollowing();
}
