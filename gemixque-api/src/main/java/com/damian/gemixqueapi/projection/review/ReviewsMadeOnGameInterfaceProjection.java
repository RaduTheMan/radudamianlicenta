package com.damian.gemixqueapi.projection.review;

import com.damian.gemixqueapi.projection.user.UserIdInterfaceProjection;

public interface ReviewsMadeOnGameInterfaceProjection extends ReviewInterfaceProjection {
    UserIdInterfaceProjection getUserEntity();
}
