package com.damian.gemixqueapi.projection.user;

import java.util.List;

public interface GetFollowersInterfaceProjection {
    List<UserIdInterfaceProjection> getFollowers();
}
