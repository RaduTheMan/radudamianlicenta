package com.damian.gemixqueapi.projection.review;

import com.damian.gemixqueapi.projection.comment.CommentMadeOnReviewInterfaceProjection;

import java.util.List;

public interface GetCommentsMadeOnReviewInterfaceProjection {
    List<CommentMadeOnReviewInterfaceProjection> getComments();
}
