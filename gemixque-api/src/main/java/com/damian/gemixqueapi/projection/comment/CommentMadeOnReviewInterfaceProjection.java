package com.damian.gemixqueapi.projection.comment;

import com.damian.gemixqueapi.projection.user.UserIdInterfaceProjection;

public interface CommentMadeOnReviewInterfaceProjection extends CommentInterfaceProjection {
    UserIdInterfaceProjection getUserEntity();
}
