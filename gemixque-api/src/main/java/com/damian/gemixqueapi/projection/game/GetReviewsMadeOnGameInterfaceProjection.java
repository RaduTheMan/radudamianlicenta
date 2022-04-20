package com.damian.gemixqueapi.projection.game;

import com.damian.gemixqueapi.projection.review.ReviewsMadeOnGameInterfaceProjection;

import java.util.List;

public interface GetReviewsMadeOnGameInterfaceProjection {
    List<ReviewsMadeOnGameInterfaceProjection> getReviewsMade();
}
