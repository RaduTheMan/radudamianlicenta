package com.damian.gemixqueapi.projection.user;

import com.damian.gemixqueapi.projection.review.ReviewsMadeByUserInterfaceProjection;

import java.util.List;

public interface GetReviewsMadeFromUserInterfaceProjection {
    List<ReviewsMadeByUserInterfaceProjection> getReviewsMade();
}
