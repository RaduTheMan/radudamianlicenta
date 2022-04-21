package com.damian.gemixqueapi.projection.review;

import com.damian.gemixqueapi.entity.UserEntity;
import com.damian.gemixqueapi.projection.user.UserIdInterfaceProjection;

import java.util.List;

public interface GetUsersLikedInterfaceProjection {
    List<UserIdInterfaceProjection> getUsersLiked();
}
